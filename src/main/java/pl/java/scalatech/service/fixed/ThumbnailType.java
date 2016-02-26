package pl.java.scalatech.service.fixed;

import lombok.Getter;

public enum ThumbnailType {
    
    SMALL(150,150),MIDDLE(250,250),LARGE(350,350),EXTRA(450,450);
    @Getter
    private int width;
    @Getter
    private int heigth;
        
    private ThumbnailType(int widht,int height){
            this.heigth = height;
            this.width = widht;                
    }       
        
}
