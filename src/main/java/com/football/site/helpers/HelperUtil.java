/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.helpers;

import com.football.site.mail.SendMail;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.log4j.Logger;

/**
 *
 * @author RIDVAN
 */
public class HelperUtil {

    /**
     * Logs exception and send email
     *
     * @param logger
     * @param e
     */
    public static void AddErrorLog(Logger logger, Exception e) {
        logger.error(e);
        //new SendMail(getStackTrace(e)).run();
    }

    public static void AddErrorLogWithString(Logger logger, String s) {
        logger.error(s);
        //new SendMail(s).run();
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * Send mail to admin
     *
     * @param message
     */
    public static void SendMail(String message) {
        new SendMail(message).run();
    }

    /**
     * Urldeki resmi istenildiği boyutta geri döndürür
     *
     * @param logger
     * @param crestUrl
     * @param width
     * @param height
     * @return
     */
    public static byte[] GetTeamPicture(Logger logger, String crestUrl, int width, int height) {
        byte[] pictureData = null;
        crestUrl = crestUrl.replaceFirst("http:", "https:");
        if (crestUrl.endsWith(".svg")) {
            try {
                pictureData = convertSVGToPNG(crestUrl, width, height);
            } catch (Exception e) {
                HelperUtil.AddErrorLog(logger, e);
            }
        } else {
            try {
                String pictureType = "png";
                if (crestUrl.endsWith("gif")) {
                    pictureType = "gif";
                } else if (crestUrl.endsWith("jpg")) {
                    pictureType = "jpg";
                } else if (crestUrl.endsWith("jpeg")) {
                    pictureType = "jpeg";
                }
                URL url = new URL(crestUrl);
                BufferedImage img = ImageIO.read(url);
                BufferedImage resizedImage = new BufferedImage(width, height, img.getType());
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(img, 0, 0, 32, 32, null);
                g.dispose();
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    ImageIO.write(resizedImage, pictureType, baos);
                    baos.flush();
                    pictureData = baos.toByteArray();
                }
            } catch (Exception e) {
                HelperUtil.AddErrorLog(logger, e);
            }

        }

        return pictureData;
    }

    public static byte[] convertSVGToPNG(String url, float width, float height) throws TranscoderException, IOException {
        ByteArrayOutputStream resultByteStream = new ByteArrayOutputStream();

        TranscoderInput transcoderInput = new TranscoderInput(url);
        TranscoderOutput transcoderOutput = new TranscoderOutput(resultByteStream);

        PNGTranscoder pngTranscoder = new PNGTranscoder();
        pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, height);
        pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, width);
        pngTranscoder.transcode(transcoderInput, transcoderOutput);

        resultByteStream.flush();

        return resultByteStream.toByteArray();
    }
}
