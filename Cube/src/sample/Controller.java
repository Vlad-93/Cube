package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Stack;
import java.util.HashMap;

import cube.blocks.*;
import cube.*;
import game.Game;

import static cube.Move.*;
import static cube.blocks.Color.*;

public class Controller {
    @FXML
    private Button       R,                   G,                   W,                   Y,                   B,                   O,
                   RG, RW, RY, RB,      GR, GW, GY, GO,      WR, WG, WB, WO,      YR, YG, YB, YO,      BR, BW, BY, BO,      OG, OW, OY, OB,
                 RGW, RGY, RWB, RYB,  GWR, GYR, GWO, GYO,  WRG, WBR, WOG, WBO,  YRG, YBR, YOG, YBO,  BRW, BRY, BOW, BOY,  OGW, OGY, OWB, OYB;

    // all buttons
    private final HashMap<Button, Color[]> buttonColors = new HashMap<>(54);
    // only 1 edge buttons
    private final HashMap<Button, Color[]> button1Colors = new HashMap<>(6);
    // only 2 edge buttons
    private final HashMap<Button, Color[]> button2Colors = new HashMap<>(24);
    // only 3 edge buttons
    private final HashMap<Button, Color[]> button3Colors = new HashMap<>(24);

    private Game game = new Game();
    private Stack<Move> stackResolve;

    private boolean isInit = false;

    private void init() {
        isInit = true;
        // buttons 1 edge
        button1Colors.put(R, new Color[]{RED});
        button1Colors.put(G, new Color[]{GREEN});
        button1Colors.put(W, new Color[]{WHITE});
        button1Colors.put(Y, new Color[]{YELLOW});
        button1Colors.put(B, new Color[]{BLUE});
        button1Colors.put(O, new Color[]{ORANGE});
        // buttons 2 edge
        button2Colors.put(RG, new Color[]{RED, GREEN});
        button2Colors.put(RW, new Color[]{RED, WHITE});
        button2Colors.put(RY, new Color[]{RED, YELLOW});
        button2Colors.put(RB, new Color[]{RED, BLUE});
        button2Colors.put(GR, new Color[]{GREEN, RED});
        button2Colors.put(GW, new Color[]{GREEN, WHITE});
        button2Colors.put(GY, new Color[]{GREEN, YELLOW});
        button2Colors.put(GO, new Color[]{GREEN, ORANGE});
        button2Colors.put(WR, new Color[]{WHITE, RED});
        button2Colors.put(WG, new Color[]{WHITE, GREEN});
        button2Colors.put(WB, new Color[]{WHITE, BLUE});
        button2Colors.put(WO, new Color[]{WHITE, ORANGE});
        button2Colors.put(YR, new Color[]{YELLOW, RED});
        button2Colors.put(YG, new Color[]{YELLOW, GREEN});
        button2Colors.put(YB, new Color[]{YELLOW, BLUE});
        button2Colors.put(YO, new Color[]{YELLOW, ORANGE});
        button2Colors.put(BR, new Color[]{BLUE, RED});
        button2Colors.put(BW, new Color[]{BLUE, WHITE});
        button2Colors.put(BY, new Color[]{BLUE, YELLOW});
        button2Colors.put(BO, new Color[]{BLUE, ORANGE});
        button2Colors.put(OG, new Color[]{ORANGE, GREEN});
        button2Colors.put(OW, new Color[]{ORANGE, WHITE});
        button2Colors.put(OY, new Color[]{ORANGE, YELLOW});
        button2Colors.put(OB, new Color[]{ORANGE, BLUE});
        // buttons 3 edges
        button3Colors.put(RGW, new Color[]{RED, GREEN, WHITE});
        button3Colors.put(RGY, new Color[]{RED, GREEN, YELLOW});
        button3Colors.put(RWB, new Color[]{RED, WHITE, BLUE});
        button3Colors.put(RYB, new Color[]{RED, YELLOW, BLUE});
        button3Colors.put(GWR, new Color[]{GREEN, WHITE, RED});
        button3Colors.put(GYR, new Color[]{GREEN, YELLOW, RED});
        button3Colors.put(GWO, new Color[]{GREEN, WHITE, ORANGE});
        button3Colors.put(GYO, new Color[]{GREEN, YELLOW, ORANGE});
        button3Colors.put(WRG, new Color[]{WHITE, RED, GREEN});
        button3Colors.put(WBR, new Color[]{WHITE, BLUE, RED});
        button3Colors.put(WOG, new Color[]{WHITE, ORANGE, GREEN});
        button3Colors.put(WBO, new Color[]{WHITE, BLUE, ORANGE});
        button3Colors.put(YRG, new Color[]{YELLOW, RED, GREEN});
        button3Colors.put(YBR, new Color[]{YELLOW, BLUE, RED});
        button3Colors.put(YOG, new Color[]{YELLOW, ORANGE, GREEN});
        button3Colors.put(YBO, new Color[]{YELLOW, BLUE, ORANGE});
        button3Colors.put(BRW, new Color[]{BLUE, RED, WHITE});
        button3Colors.put(BRY, new Color[]{BLUE, RED, YELLOW});
        button3Colors.put(BOW, new Color[]{BLUE, ORANGE, WHITE});
        button3Colors.put(BOY, new Color[]{BLUE, ORANGE, YELLOW});
        button3Colors.put(OGW, new Color[]{ORANGE, GREEN, WHITE});
        button3Colors.put(OGY, new Color[]{ORANGE, GREEN, YELLOW});
        button3Colors.put(OWB, new Color[]{ORANGE, WHITE, BLUE});
        button3Colors.put(OYB, new Color[]{ORANGE, YELLOW, BLUE});
        // all buttons edges
        buttonColors.putAll(button1Colors);
        buttonColors.putAll(button2Colors);
        buttonColors.putAll(button3Colors);
    }

    @FXML
    private GridPane Red, Green, White, Yellow, Blue, Orange;

    @FXML
    private Button undo, redo, mix, resolve, restart, move;

    @FXML
    private Label undoScore, redoScore, resolveScore;


    // button push:
    @FXML
    private void redLeftPush() {
        step(RED_LEFT);
    }
    @FXML
    private void redRightPush() {
        step(RED_RIGHT);
    }
    @FXML
    private void redReversePush() {
        step(RED_REVERSE);
    }

    @FXML
    private void greenLeftPush() {
        step(GREEN_LEFT);
    }
    @FXML
    private void greenRightPush() {
        step(GREEN_RIGHT);
    }
    @FXML
    private void greenReversePush() {
        step(GREEN_REVERSE);
    }

    @FXML
    private void whiteLeftPush() {
        step(WHITE_LEFT);
    }
    @FXML
    private void whiteRightPush() {
        step(WHITE_RIGHT);
    }
    @FXML
    private void whiteReversePush() {
        step(WHITE_REVERSE);
    }

    @FXML
    private void yellowLeftPush()  {
        step(YELLOW_LEFT);
    }
    @FXML
    private void yellowRightPush() {
        step(YELLOW_RIGHT);
    }
    @FXML
    private void yellowReversePush() {
        step(YELLOW_REVERSE);
    }

    @FXML
    private void blueLeftPush() {
        step(BLUE_LEFT);
    }
    @FXML
    private void blueRightPush() {
        step(BLUE_RIGHT);
    }
    @FXML
    private void blueReversePush() {
        step(BLUE_REVERSE);
    }

    @FXML
    private void orangeLeftPush() {
        step(ORANGE_LEFT);
    }
    @FXML
    private void orangeRightPush()  {
        step(ORANGE_RIGHT);
    }
    @FXML
    private void orangeReversePush() {
        step(ORANGE_REVERSE);
    }

    // rotation:
//    private void redLeft() {
//        String style = RG.getStyle();
//        RG.setStyle(RW.getStyle());
//        RW.setStyle(RB.getStyle());
//        RB.setStyle(RY.getStyle());
//        RY.setStyle(style);
//
//        style = GR.getStyle();
//        GR.setStyle(WR.getStyle());
//        WR.setStyle(BR.getStyle());
//        BR.setStyle(YR.getStyle());
//        YR.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(RWB.getStyle());
//        RWB.setStyle(RYB.getStyle());
//        RYB.setStyle(RGY.getStyle());
//        RGY.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(WBR.getStyle());
//        WBR.setStyle(BRY.getStyle());
//        BRY.setStyle(YRG.getStyle());
//        YRG.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(BRW.getStyle());
//        BRW.setStyle(YBR.getStyle());
//        YBR.setStyle(GYR.getStyle());
//        GYR.setStyle(style);
//    }
//    private void redRight() {
//        String style = RG.getStyle();
//        RG.setStyle(RY.getStyle());
//        RY.setStyle(RB.getStyle());
//        RB.setStyle(RW.getStyle());
//        RW.setStyle(style);
//
//        style = GR.getStyle();
//        GR.setStyle(YR.getStyle());
//        YR.setStyle(BR.getStyle());
//        BR.setStyle(WR.getStyle());
//        WR.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(RGY.getStyle());
//        RGY.setStyle(RYB.getStyle());
//        RYB.setStyle(RWB.getStyle());
//        RWB.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(YRG.getStyle());
//        YRG.setStyle(BRY.getStyle());
//        BRY.setStyle(WBR.getStyle());
//        WBR.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(GYR.getStyle());
//        GYR.setStyle(YBR.getStyle());
//        YBR.setStyle(BRW.getStyle());
//        BRW.setStyle(style);
//    }
//    private void redReverse() {
//        String style = RG.getStyle();
//        RG.setStyle(RB.getStyle());
//        RB.setStyle(style);
//        style = RY.getStyle();
//        RY.setStyle(RW.getStyle());
//        RW.setStyle(style);
//
//        style = GR.getStyle();
//        GR.setStyle(BR.getStyle());
//        BR.setStyle(style);
//        style = YR.getStyle();
//        YR.setStyle(WR.getStyle());
//        WR.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(RYB.getStyle());
//        RYB.setStyle(style);
//        style = RGY.getStyle();
//        RGY.setStyle(RWB.getStyle());
//        RWB.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(BRY.getStyle());
//        BRY.setStyle(style);
//        style = YRG.getStyle();
//        YRG.setStyle(WBR.getStyle());
//        WBR.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(YBR.getStyle());
//        YBR.setStyle(style);
//        style = GYR.getStyle();
//        GYR.setStyle(BRW.getStyle());
//        BRW.setStyle(style);
//    }
//
//    private void greenLeft() {
//        String style = GO.getStyle();
//        GO.setStyle(GW.getStyle());
//        GW.setStyle(GR.getStyle());
//        GR.setStyle(GY.getStyle());
//        GY.setStyle(style);
//
//        style = OG.getStyle();
//        OG.setStyle(WG.getStyle());
//        WG.setStyle(RG.getStyle());
//        RG.setStyle(YG.getStyle());
//        YG.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(GWR.getStyle());
//        GWR.setStyle(GYR.getStyle());
//        GYR.setStyle(GYO.getStyle());
//        GYO.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(WRG.getStyle());
//        WRG.setStyle(RGY.getStyle());
//        RGY.setStyle(YOG.getStyle());
//        YOG.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(RGW.getStyle());
//        RGW.setStyle(YRG.getStyle());
//        YRG.setStyle(OGY.getStyle());
//        OGY.setStyle(style);
//    }
//    private void greenRight() {
//        String style = GO.getStyle();
//        GO.setStyle(GY.getStyle());
//        GY.setStyle(GR.getStyle());
//        GR.setStyle(GW.getStyle());
//        GW.setStyle(style);
//
//        style = OG.getStyle();
//        OG.setStyle(YG.getStyle());
//        YG.setStyle(RG.getStyle());
//        RG.setStyle(WG.getStyle());
//        WG.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(GYO.getStyle());
//        GYO.setStyle(GYR.getStyle());
//        GYR.setStyle(GWR.getStyle());
//        GWR.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(YOG.getStyle());
//        YOG.setStyle(RGY.getStyle());
//        RGY.setStyle(WRG.getStyle());
//        WRG.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(OGY.getStyle());
//        OGY.setStyle(YRG.getStyle());
//        YRG.setStyle(RGW.getStyle());
//        RGW.setStyle(style);
//    }
//    private void greenReverse() {
//        String style = GO.getStyle();
//        GO.setStyle(GR.getStyle());
//        GR.setStyle(style);
//        style = GW.getStyle();
//        GW.setStyle(GY.getStyle());
//        GY.setStyle(style);
//
//        style = OG.getStyle();
//        OG.setStyle(RG.getStyle());
//        RG.setStyle(style);
//        style = WG.getStyle();
//        WG.setStyle(YG.getStyle());
//        YG.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(GYR.getStyle());
//        GYR.setStyle(style);
//        style = GWR.getStyle();
//        GWR.setStyle(GYO.getStyle());
//        GYO.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(RGY.getStyle());
//        RGY.setStyle(style);
//        style = WRG.getStyle();
//        WRG.setStyle(YOG.getStyle());
//        YOG.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(YRG.getStyle());
//        YRG.setStyle(style);
//        style = RGW.getStyle();
//        RGW.setStyle(OGY.getStyle());
//        OGY.setStyle(style);
//    }
//
//    private void whiteLeft() {
//        String style = WR.getStyle();
//        WR.setStyle(WG.getStyle());
//        WG.setStyle(WO.getStyle());
//        WO.setStyle(WB.getStyle());
//        WB.setStyle(style);
//
//        style = RW.getStyle();
//        RW.setStyle(GW.getStyle());
//        GW.setStyle(OW.getStyle());
//        OW.setStyle(BW.getStyle());
//        BW.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(WOG.getStyle());
//        WOG.setStyle(WBO.getStyle());
//        WBO.setStyle(WBR.getStyle());
//        WBR.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(GWO.getStyle());
//        GWO.setStyle(OWB.getStyle());
//        OWB.setStyle(BRW.getStyle());
//        BRW.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(OGW.getStyle());
//        OGW.setStyle(BOW.getStyle());
//        BOW.setStyle(RWB.getStyle());
//        RWB.setStyle(style);
//    }
//    private void whiteRight() {
//        String style = WR.getStyle();
//        WR.setStyle(WB.getStyle());
//        WB.setStyle(WO.getStyle());
//        WO.setStyle(WG.getStyle());
//        WG.setStyle(style);
//
//        style = RW.getStyle();
//        RW.setStyle(BW.getStyle());
//        BW.setStyle(OW.getStyle());
//        OW.setStyle(GW.getStyle());
//        GW.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(WBR.getStyle());
//        WBR.setStyle(WBO.getStyle());
//        WBO.setStyle(WOG.getStyle());
//        WOG.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(BRW.getStyle());
//        BRW.setStyle(OWB.getStyle());
//        OWB.setStyle(GWO.getStyle());
//        GWO.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(RWB.getStyle());
//        RWB.setStyle(BOW.getStyle());
//        BOW.setStyle(OGW.getStyle());
//        OGW.setStyle(style);
//    }
//    private void whiteReverse() {
//        String style = WR.getStyle();
//        WR.setStyle(WO.getStyle());
//        WO.setStyle(style);
//        style = WG.getStyle();
//        WG.setStyle(WB.getStyle());
//        WB.setStyle(style);
//
//        style = RW.getStyle();
//        RW.setStyle(OW.getStyle());
//        OW.setStyle(style);
//        style = GW.getStyle();
//        GW.setStyle(BW.getStyle());
//        BW.setStyle(style);
//
//        style = WRG.getStyle();
//        WRG.setStyle(WBO.getStyle());
//        WBO.setStyle(style);
//        style = WOG.getStyle();
//        WOG.setStyle(WBR.getStyle());
//        WBR.setStyle(style);
//
//        style = RGW.getStyle();
//        RGW.setStyle(OWB.getStyle());
//        OWB.setStyle(style);
//        style = GWO.getStyle();
//        GWO.setStyle(BRW.getStyle());
//        BRW.setStyle(style);
//
//        style = GWR.getStyle();
//        GWR.setStyle(BOW.getStyle());
//        BOW.setStyle(style);
//        style = OGW.getStyle();
//        OGW.setStyle(RWB.getStyle());
//        RWB.setStyle(style);
//    }
//
//    private void yellowLeft()  {
//        String style = YR.getStyle();
//        YR.setStyle(YB.getStyle());
//        YB.setStyle(YO.getStyle());
//        YO.setStyle(YG.getStyle());
//        YG.setStyle(style);
//
//        style = RY.getStyle();
//        RY.setStyle(BY.getStyle());
//        BY.setStyle(OY.getStyle());
//        OY.setStyle(GY.getStyle());
//        GY.setStyle(style);
//
//        style = YRG.getStyle();
//        YRG.setStyle(YBR.getStyle());
//        YBR.setStyle(YBO.getStyle());
//        YBO.setStyle(YOG.getStyle());
//        YOG.setStyle(style);
//
//        style = RGY.getStyle();
//        RGY.setStyle(BRY.getStyle());
//        BRY.setStyle(OYB.getStyle());
//        OYB.setStyle(GYO.getStyle());
//        GYO.setStyle(style);
//
//        style = GYR.getStyle();
//        GYR.setStyle(RYB.getStyle());
//        RYB.setStyle(BOY.getStyle());
//        BOY.setStyle(OGY.getStyle());
//        OGY.setStyle(style);
//    }
//    private void yellowRight() {
//        String style = YR.getStyle();
//        YR.setStyle(YG.getStyle());
//        YG.setStyle(YO.getStyle());
//        YO.setStyle(YB.getStyle());
//        YB.setStyle(style);
//
//        style = RY.getStyle();
//        RY.setStyle(GY.getStyle());
//        GY.setStyle(OY.getStyle());
//        OY.setStyle(BY.getStyle());
//        BY.setStyle(style);
//
//        style = YRG.getStyle();
//        YRG.setStyle(YOG.getStyle());
//        YOG.setStyle(YBO.getStyle());
//        YBO.setStyle(YBR.getStyle());
//        YBR.setStyle(style);
//
//        style = RGY.getStyle();
//        RGY.setStyle(GYO.getStyle());
//        GYO.setStyle(OYB.getStyle());
//        OYB.setStyle(BRY.getStyle());
//        BRY.setStyle(style);
//
//        style = GYR.getStyle();
//        GYR.setStyle(OGY.getStyle());
//        OGY.setStyle(BOY.getStyle());
//        BOY.setStyle(RYB.getStyle());
//        RYB.setStyle(style);
//    }
//    private void yellowReverse() {
//        String style = YR.getStyle();
//        YR.setStyle(YO.getStyle());
//        YO.setStyle(style);
//        style = YG.getStyle();
//        YG.setStyle(YB.getStyle());
//        YB.setStyle(style);
//
//        style = RY.getStyle();
//        RY.setStyle(OY.getStyle());
//        OY.setStyle(style);
//        style = GY.getStyle();
//        GY.setStyle(BY.getStyle());
//        BY.setStyle(style);
//
//        style = YRG.getStyle();
//        YRG.setStyle(YBO.getStyle());
//        YBO.setStyle(style);
//        style = YOG.getStyle();
//        YOG.setStyle(YBR.getStyle());
//        YBR.setStyle(style);
//
//        style = RGY.getStyle();
//        RGY.setStyle(OYB.getStyle());
//        OYB.setStyle(style);
//        style = GYO.getStyle();
//        GYO.setStyle(BRY.getStyle());
//        BRY.setStyle(style);
//
//        style = GYR.getStyle();
//        GYR.setStyle(BOY.getStyle());
//        BOY.setStyle(style);
//        style = OGY.getStyle();
//        OGY.setStyle(RYB.getStyle());
//        RYB.setStyle(style);
//    }
//
//    private void blueLeft() {
//        String style = BO.getStyle();
//        BO.setStyle(BY.getStyle());
//        BY.setStyle(BR.getStyle());
//        BR.setStyle(BW.getStyle());
//        BW.setStyle(style);
//
//        style = OB.getStyle();
//        OB.setStyle(YB.getStyle());
//        YB.setStyle(RB.getStyle());
//        RB.setStyle(WB.getStyle());
//        WB.setStyle(style);
//
//        style = BOW.getStyle();
//        BOW.setStyle(BOY.getStyle());
//        BOY.setStyle(BRY.getStyle());
//        BRY.setStyle(BRW.getStyle());
//        BRW.setStyle(style);
//
//        style = OWB.getStyle();
//        OWB.setStyle(YBO.getStyle());
//        YBO.setStyle(RYB.getStyle());
//        RYB.setStyle(WBR.getStyle());
//        WBR.setStyle(style);
//
//        style = WBO.getStyle();
//        WBO.setStyle(OYB.getStyle());
//        OYB.setStyle(YBR.getStyle());
//        YBR.setStyle(RWB.getStyle());
//        RWB.setStyle(style);
//    }
//    private void blueRight() {
//        String style = BO.getStyle();
//        BO.setStyle(BW.getStyle());
//        BW.setStyle(BR.getStyle());
//        BR.setStyle(BY.getStyle());
//        BY.setStyle(style);
//
//        style = OB.getStyle();
//        OB.setStyle(WB.getStyle());
//        WB.setStyle(RB.getStyle());
//        RB.setStyle(YB.getStyle());
//        YB.setStyle(style);
//
//        style = BOW.getStyle();
//        BOW.setStyle(BRW.getStyle());
//        BRW.setStyle(BRY.getStyle());
//        BRY.setStyle(BOY.getStyle());
//        BOY.setStyle(style);
//
//        style = OWB.getStyle();
//        OWB.setStyle(WBR.getStyle());
//        WBR.setStyle(RYB.getStyle());
//        RYB.setStyle(YBO.getStyle());
//        YBO.setStyle(style);
//
//        style = WBO.getStyle();
//        WBO.setStyle(RWB.getStyle());
//        RWB.setStyle(YBR.getStyle());
//        YBR.setStyle(OYB.getStyle());
//        OYB.setStyle(style);
//    }
//    private void blueReverse() {
//        String style = BO.getStyle();
//        BO.setStyle(BR.getStyle());
//        BR.setStyle(style);
//        style = BW.getStyle();
//        BW.setStyle(BY.getStyle());
//        BY.setStyle(style);
//
//        style = OB.getStyle();
//        OB.setStyle(RB.getStyle());
//        RB.setStyle(style);
//        style = WB.getStyle();
//        WB.setStyle(YB.getStyle());
//        YB.setStyle(style);
//
//        style = BOW.getStyle();
//        BOW.setStyle(BRY.getStyle());
//        BRY.setStyle(style);
//        style = BRW.getStyle();
//        BRW.setStyle(BOY.getStyle());
//        BOY.setStyle(style);
//
//        style = OWB.getStyle();
//        OWB.setStyle(RYB.getStyle());
//        RYB.setStyle(style);
//        style = WBR.getStyle();
//        WBR.setStyle(YBO.getStyle());
//        YBO.setStyle(style);
//
//        style = WBO.getStyle();
//        WBO.setStyle(YBR.getStyle());
//        YBR.setStyle(style);
//        style = RWB.getStyle();
//        RWB.setStyle(OYB.getStyle());
//        OYB.setStyle(style);
//    }
//
//    private void orangeLeft() {
//        String style = OG.getStyle();
//        OG.setStyle(OY.getStyle());
//        OY.setStyle(OB.getStyle());
//        OB.setStyle(OW.getStyle());
//        OW.setStyle(style);
//
//        style = GO.getStyle();
//        GO.setStyle(YO.getStyle());
//        YO.setStyle(BO.getStyle());
//        BO.setStyle(WO.getStyle());
//        WO.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(OGY.getStyle());
//        OGY.setStyle(OYB.getStyle());
//        OYB.setStyle(OWB.getStyle());
//        OWB.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(YOG.getStyle());
//        YOG.setStyle(BOY.getStyle());
//        BOY.setStyle(WBO.getStyle());
//        WBO.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(GYO.getStyle());
//        GYO.setStyle(YBO.getStyle());
//        YBO.setStyle(BOW.getStyle());
//        BOW.setStyle(style);
//    }
//    private void orangeRight()  {
//        String style = OG.getStyle();
//        OG.setStyle(OW.getStyle());
//        OW.setStyle(OB.getStyle());
//        OB.setStyle(OY.getStyle());
//        OY.setStyle(style);
//
//        style = GO.getStyle();
//        GO.setStyle(WO.getStyle());
//        WO.setStyle(BO.getStyle());
//        BO.setStyle(YO.getStyle());
//        YO.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(OWB.getStyle());
//        OWB.setStyle(OYB.getStyle());
//        OYB.setStyle(OGY.getStyle());
//        OGY.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(WBO.getStyle());
//        WBO.setStyle(BOY.getStyle());
//        BOY.setStyle(YOG.getStyle());
//        YOG.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(BOW.getStyle());
//        BOW.setStyle(YBO.getStyle());
//        YBO.setStyle(GYO.getStyle());
//        GYO.setStyle(style);
//    }
//    private void orangeReverse() {
//        String style = OG.getStyle();
//        OG.setStyle(OB.getStyle());
//        OB.setStyle(style);
//        style = OY.getStyle();
//        OY.setStyle(OW.getStyle());
//        OW.setStyle(style);
//
//        style = GO.getStyle();
//        GO.setStyle(BO.getStyle());
//        BO.setStyle(style);
//        style = YO.getStyle();
//        YO.setStyle(WO.getStyle());
//        WO.setStyle(style);
//
//        style = OGW.getStyle();
//        OGW.setStyle(OYB.getStyle());
//        OYB.setStyle(style);
//        style = OGY.getStyle();
//        OGY.setStyle(OWB.getStyle());
//        OWB.setStyle(style);
//
//        style = GWO.getStyle();
//        GWO.setStyle(BOY.getStyle());
//        BOY.setStyle(style);
//        style = YOG.getStyle();
//        YOG.setStyle(WBO.getStyle());
//        WBO.setStyle(style);
//
//        style = WOG.getStyle();
//        WOG.setStyle(YBO.getStyle());
//        YBO.setStyle(style);
//        style = GYO.getStyle();
//        GYO.setStyle(BOW.getStyle());
//        BOW.setStyle(style);
//    }

    private void step(Move move) {
        game.step(move);
        show();
//        switch (move) {
//            case RED_LEFT:
//                redLeft();
//                break;
//            case RED_RIGHT:
//                redRight();
//                break;
//            case RED_REVERSE:
//                redReverse();
//                break;
//
//            case GREEN_LEFT:
//                greenLeft();
//                break;
//            case GREEN_RIGHT:
//                greenRight();
//                break;
//            case GREEN_REVERSE:
//                greenReverse();
//                break;
//
//            case WHITE_LEFT:
//                whiteLeft();
//                break;
//            case WHITE_RIGHT:
//                whiteRight();
//                break;
//            case WHITE_REVERSE:
//                whiteReverse();
//                break;
//
//            case YELLOW_LEFT:
//                yellowLeft();
//                break;
//            case YELLOW_RIGHT:
//                yellowRight();
//                break;
//            case YELLOW_REVERSE:
//                yellowReverse();
//                break;
//
//            case BLUE_LEFT:
//                blueLeft();
//                break;
//            case BLUE_RIGHT:
//                blueRight();
//                break;
//            case BLUE_REVERSE:
//                blueReverse();
//                break;
//
//            case ORANGE_LEFT:
//                orangeLeft();
//                break;
//            case ORANGE_RIGHT:
//                orangeRight();
//                break;
//            case ORANGE_REVERSE:
//                orangeReverse();
//                break;
//        }
    }

    @FXML
    private void undo() {
        if (game.takeBack())
            show();
    }

    @FXML
    private void redo() {
        if (game.repeat())
            show();
    }

    @FXML
    private void mix() {
        game.mix();
        show();
    }

    @FXML
    private void resolve() {
        stackResolve = game.resolve();
        resolveShow();
    }

    @FXML
    private void resolveMove() {
        Move move = stackResolve.pop();
        step(move);

        resolveShow();
    }

    @FXML
    private void newGame() {
        game = new Game();
        show();
    }

    private void show() {
        Cube cube = game.getCube();

        if (!isInit)
            init();

        for (Button button : buttonColors.keySet()) {
            Color[] colors = buttonColors.get(button);
            Block block = cube.getCurrentBlock(colors);
            Color[] edges = block.getEdges();

            for (int i = 0; i < colors.length; i++)
                if (colors[0] == edges[i])
                    button.setStyle("-fx-background-color: " + block.getColors()[i]);
        }

        undoScore.setText(Integer.toString(game.getUndoScore()));
        redoScore.setText(Integer.toString(game.getRedoScore()));

        resolveScore.setText("");
        move.setVisible(false);
    }

    private void resolveShow() {
        String text;

        if (stackResolve == null) {
            text = "N/A";
            move.setVisible(false);
        } else if (stackResolve.isEmpty()) {
            text = "";
            move.setVisible(false);
        } else {
            text = String.valueOf(stackResolve.size());
            move.setVisible(true);

            Color color = stackResolve.peek().getColor();
            Direction direction = stackResolve.peek().getDirection();

            switch (direction) {
                case RIGHT:
                    move.setText("Move ⟳");
                    break;
                case REVERSE:
                    move.setText("Move ⮁");
                    break;
                case LEFT:
                    move.setText("Move ⟲");
                    break;
            }

            move.setStyle("-fx-background-color: " + color.toHex());
        }

        resolveScore.setText(text);
    }

}
