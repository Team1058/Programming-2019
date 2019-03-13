package org.pvcpirates.frc2019.gamepads;

public enum ButtonPadEnum {

    FLIPPER_X(1),
    FLIPPER_Y(2),
    GAMEPIECE_SWITCH(4),
    SCORE_HIGH(5),
    SCORE_MIDDLE(7),
    SCORE_LOW(8),
    PICKUP(9),
    SUPER_CARGO(10),
    CLIMB_FRONT(11),
    CLIMB_REAR(12),
    CLIMB_SWITCH(13),
    
    ENABLE_MANUAL(16),
    ROLLERS_IN(5),
    ROLLERS_OUT(7),
    CLOSE_GRABBER(8),
    RETRACT_ALL(9),
    ZERO_ALL(10);
    

    int val;
    
    ButtonPadEnum(int val){
        this.val = val;
    }
}
