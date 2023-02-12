
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener{
    JFrame f;
    JTextArea t;
    //constructor
    note(){
        //initialising the frame
        f = new JFrame("notepad");
        //initialising the textarea
        t = new JTextArea();

        //creating menu bar
        JMenuBar menubar = new JMenuBar();

        //creating the file menu
        JMenu file = new JMenu("File");

        //creating the options for file menu
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");

        //adding acionListener to each of the options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding options to file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //creating the edit menu
        JMenu edit = new JMenu("Edit");

        //creating the options for edit menu
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        //adding acionListener to each of the options
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        //adding options to edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        // create close menu
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);
        // adding menus to menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        f.setJMenuBar(menubar);
        f.add(t);
        f.setSize(1280,720);
        f.show();

    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        switch(s){
            case "New":
                t.setText("");
                break;
            case "Save":
                //create the object of jfilechoser  class with starting path of d:
                JFileChooser j= new JFileChooser("D:");

                // invoke savedialogbox
                int r = j.showSaveDialog(null);
                // if save is clicked r will be 1 and if cancel is clicked r will be 0
                if(r == 0){
                    //declare a file of selectedLocation
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try{
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();
                    }
                    catch (IOException ex){
                        throw new RuntimeException(ex);
                    }
                }

                else{
                    JOptionPane.showMessageDialog(f, "the user has cancelled the operation");
                }


                break;
            case "Open":
                //create the object of jfilechoser  class with starting path of d:
                JFileChooser ji= new JFileChooser("D:");

                // invoke savedialogbox
                int ri = ji.showOpenDialog(null);
                // if save is clicked r will be 1 and if cancel is clicked r will be 0
                if(ri == 0){
                    //declare a file of selectedLocation
                    File fii = new File(ji.getSelectedFile().getAbsolutePath());

                    try{
                        FileReader fr = new FileReader(fii);
                        BufferedReader br = new BufferedReader(fr);

                        String s1 = br.readLine();
                        String s2;
                        while((s2 = br.readLine()) != null){
                            s1 = s1 + "\n" + s2;
                        }

                        t.setText(s1);
                    }
                    catch (IOException ex){
                        throw new RuntimeException(ex);
                    }
                }

                else{
                    JOptionPane.showMessageDialog(f, "the user has cancelled the operation");
                }
                break;
            case "Print":
                try {
                    t.print();
                }
                catch(PrinterException ex){
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + s);
        }
    }

    public static void main(String arg[]){
        note n = new note();
    }
}

