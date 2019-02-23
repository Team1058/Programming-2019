package org.pvcpirates.frc2019.gamepads;

public enum ButtonPadEnum {

    HATCH_LOW(0),
    HATCH_MID(1),
    HATCH_HIGH(2),
    CARGO_LOW(3),
    CARGO_MID(4),
    CLIMB_SWITCH(5),
    CLIMB_FRONT(6),
    CLIMB_BACK(7),
    CARGO_CAPTURE_NORMAL(8),
    CARGO_CAPTURE_SUPER(9),
    HATCH_CAPTURE(10),
    MANUAL_ZERO(11),
    MANUAL_CLOSE_GRABBER(12),
    MANUAL_RETRACT_ALL(13),
    MANUAL_CARGO_IN(14),
    MANUAL_CARGO_OUT(15),
    MANUAL_DISABLE_DRIVE(16);

    int val;
    
    ButtonPadEnum(int val){
        this.val = val;
    }
}
