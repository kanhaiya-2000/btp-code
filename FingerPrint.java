import com.digitalpersona.uareu.*;

public class FingerprintCapture {
  String B1 = "",B2 = "";
  public static void main(String[] args) {
    try {
      // Initialize the fingerprint reader
      Reader reader = UareUGlobal.GetReaderModule().GetDevice();
      reader.Open(Reader.Priority.COOPERATIVE);

      // Capture the fingerprint image
      CaptureResult captureResult = reader.Capture(
          Fid.Format.ANSI_381_2004,
          Reader.ImageProcessing.IMG_PROC_DEFAULT,
          5000,
          Reader.CaptureTimeout.NONE,
          null
      );

      // Get the fingerprint image data
      Fid fid = captureResult.image;
      byte[] rawImage = fid.getImageData();
      int width = fid.getView(0).getWidth();
      int height = fid.getView(0).getHeight();
      int[] pixels = new int[width * height];
      RawToBitmap(rawImage, pixels, width, height);

      // Process the fingerprint image
      // ... process pixels[] into B1', B2'
      
      int HALF_LENGTH = pixels.length/2;
      for(int i = 0;i<HALF_LENGTH;i++){
         B1 += (char)(pixels[i]);
         B2 += (char)(pixels[HALF_LENGTH + i]);
      }
      // Close the fingerprint reader
      reader.Close();
    } catch (UareUException e) {
      System.err.println("Error capturing fingerprint: " + e.getMessage());
    }
  }

  private static void RawToBitmap(byte[] raw, int[] pixels, int width, int height) {
    int[] rawPixels = new int[width * height];
    for (int i = 0; i < raw.length; i++) {
      int pixel = raw[i] & 0xff;
      rawPixels[i] = 0xff000000 | pixel << 16 | pixel << 8 | pixel;
    }

    for (int i = 0; i < height; i++) {
      int index = i * width;
      for (int j = 0; j < width; j++) {
        pixels[index + j] = rawPixels[index + (width - j - 1)];
      }
    }
  }

  public String[] getSecrets(){
    String []secrets = {B1,B2};
    return secrets;
  }
}
