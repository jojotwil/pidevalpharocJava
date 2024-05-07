package View;

import Entities.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
public class QRCodeGenerator {
    public ImageView qrCodeImageView;




    @FXML
    void generateQRCode(String text, int width, int height) {
        try {
            // Générer le code QR
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(text).withSize(width, height).to(ImageType.PNG).stream();

            // Convertir le flux d'octets en image JavaFX
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            Image qrCodeImage = new Image(byteArrayInputStream);

            // Afficher l'image dans votre application JavaFX
            qrCodeImageView.setImage(qrCodeImage); // Assurez-vous d'avoir un ImageView nommé qrCodeImageView dans votre FXML
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
