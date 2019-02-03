import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PrettyXML extends JFrame {

    private PrettyXML() throws HeadlessException {
        super("PrettyXML");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(720, 1152));

        final JTextArea textArea = new JTextArea("");
        this.add(textArea);

        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        systemClipboard.addFlavorListener(flavorEvent -> {
            try {
                System.out.println("Flavor event from " + flavorEvent.getSource());

                final Object systemClipboardData = systemClipboard.getData(DataFlavor.stringFlavor);
                if ((systemClipboardData != null) && String.class.equals(systemClipboardData.getClass())) {
                    textArea.setText((String) systemClipboardData);
                }
            } catch (final UnsupportedFlavorException | IOException exception) {
                textArea.setText(exception.getMessage());
            }
        });

        this.pack();
        this.setVisible(true);
    }

    public static void main(final String[] args) {
        new PrettyXML();
    }
}
