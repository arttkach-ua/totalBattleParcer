/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ta.total_battle_analizer;

import com.lowagie.text.Document;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static org.apache.commons.io.IOUtils.writer;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;



/**
 *
 * @author Artem
 */
public class tools {
private int _x, _y, _width, _height;
File infoFile;

public void makeScreenShot(int imageID) throws AWTException
{
    _x = 500;
    _y = 180;
    _height = 24;
    _width = 400;
    try {
        Robot robot;
        robot = new Robot();
        Rectangle area;
        BufferedImage bufferedImage;
        //region box_name
        //Rectangle area = new Rectangle(_x+4, _y, _width, _height);
        //BufferedImage bufferedImage = robot.createScreenCapture(area);
        //Запись в изображение
        //ImageIO.write(bufferedImage, "JPG", new File("d://total_battle_analizer/box_name.jpg"));        
        //endregion
        
        //region box_name
        area = new Rectangle(_x+40, _y+_height, _width-50, _height);
        bufferedImage = robot.createScreenCapture(area);
        //Запись в изображение
        ImageIO.write(bufferedImage, "JPG", new File("g://total_battle_analizer/gamer.jpg"));
        ImageIO.write(bufferedImage, "JPG", new File("g://total_battle_analizer/log_img/gamer" + imageID + ".jpg"));        
        //endregion
        
        //region box_source
        area = new Rectangle(_x+82, _y+_height*2, _width, _height);
        bufferedImage = robot.createScreenCapture(area);
        //Запись в изображение
        ImageIO.write(bufferedImage, "JPG", new File("g://total_battle_analizer/box_source.jpg"));
        //ImageIO.write(bufferedImage, "JPG", new File("d://total_battle_analizer/log_img/box_source" + imageID + ".jpg"));          
        //endregion
        
        //region time
        area = new Rectangle(_x+533, _y+_height, _width-300, _height);
        bufferedImage = robot.createScreenCapture(area);
        //Запись в изображение
        ImageIO.write(bufferedImage, "JPG", new File("g://total_battle_analizer/box_time.jpg"));        
        //ImageIO.write(bufferedImage, "JPG", new File("d://total_battle_analizer/log_img/box_time" + imageID + ".jpg"));                
        //endregion
  
    } catch (Exception e) {
        
    }
   

}
public void clickButton() throws AWTException{
    int button_coord_x, button_coord_y;
    button_coord_x = 1050;
    button_coord_y = 250;
    
    Robot bot = new Robot();
    bot.mouseMove(button_coord_x, button_coord_y);    
    bot.mousePress(InputEvent.BUTTON1_MASK);
    bot.mouseRelease(InputEvent.BUTTON1_MASK);

}

public Object[] RecordnezeText(int imageID){
    Tesseract tesseract = new Tesseract();
    Object[] info = new Object[4];
   
    try {
	tesseract.setDatapath("D://Java/text recordnize/Tess4J/tessdata");
        tesseract.setLanguage("rus");
        // the path of your tess data folder
	// inside the extracted file

        String box_name = "1";
        //String box_name = tesseract.doOCR(new File("d://total_battle_analizer/box_name.jpg"));
	//System.out.print(box_name);
        
        String gamer = tesseract.doOCR(new File("g://total_battle_analizer/gamer.jpg"));
	//System.out.print(gamer);
        String box_source = tesseract.doOCR(new File("g://total_battle_analizer/box_source.jpg"));
	//System.out.print(box_source);
        String box_time = tesseract.doOCR(new File("g://total_battle_analizer/box_time.jpg"));
	//System.out.print(box_time);
        
        info[0] = box_name.trim();
        info[1] = gamer.trim();
        info[2] = box_source.trim();
        info[3] = box_time.trim();
        
	}
	catch (TesseractException e) {
            e.printStackTrace();
	}
    finally
    {
    return info;
    }
}

public void writeTXT(Object[] info, int imageID) throws Exception{
    String path = "G://total_battle_analizer//info.txt";
    
    File file = new File(path);
    try {
        FileOutputStream fos = new FileOutputStream(file,true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        
        writer.append(Integer.toString(imageID));
        writer.append("&&&");
        writer.append((String) info[1]);
        writer.append("&&&");
        writer.append((String) info[2]);
        writer.append("&&&");
        writer.append(df.format(new Date()));
        writer.append("&&&");
        writer.append((String) info[3]);
        //writer.write("\r\n");
        writer.newLine();
        writer.close();
        fos.close();
        //os.close();
        osw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}

