package org.pvcpirates.frc2019.gamepads;

public enum ButtonPadEnum {

    HATCH_LOW(0),
    HATCH_MID(1),
    HATCH_HIGH(2),
    CARGO_LOW(3),
    CARGO_MID(4),
    CARGO_HIGH(5),
    CLIMB_SWITCH_LEVEL2(6),
    CLIMB_SWITCH_LEVEL3(7),
    CLIMB_FRONT(8),
    CLIMB_BACK(9),
    CARGO_CAPTURE_NORMAL(10),
    CARGO_CAPTURE_SUPER(11),
    HATCH_CAPTURE(12),
    MANUAL_ZERO(13),
    MANUAL_CLOSE_GRABBER(14),
    MANUAL_RETRACT_ALL(15),
    MANUAL_CARGO_IN(16),
    MANUAL_CARGO_OUT(17),
    MANUAL_DISABLE_DRIVE(18);

    int val;
    
    ButtonPadEnum(int val){
        this.val = val;
    }
}
