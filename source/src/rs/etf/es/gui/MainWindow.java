/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on 21.05.2011., 16.54.43
 */
package rs.etf.es.gui;

import java.util.ArrayList;
import rs.etf.es.graphics.Block;
import rs.etf.es.logic.StripsLogic;

/**
 *
 * @author MB
 */
public class MainWindow extends javax.swing.JFrame {

    private StripsLogic strips = null;
    private GUIWorkspace currWorkspace, goalWorkspace;
    private ArrayList<String> currBlockNames, goalBlockNames;

    /** Creates new form MainWindow */
    public MainWindow() {
        currWorkspace = new GUIWorkspace();
        goalWorkspace = new GUIWorkspace();

        currBlockNames = new ArrayList<String>();
        goalBlockNames = new ArrayList<String>();

        currWorkspace.makeRobotArm(410, 0);
        goalWorkspace.makeRobotArm(410, 0);

        initComponents();

        currWorkspace.repaint();
        goalWorkspace.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currButtonGroup = new javax.swing.ButtonGroup();
        currButtonGroup.add(currBlackRadioButton);
        currButtonGroup.add(currWhiteRadioButton);
        goalButtonGroup = new javax.swing.ButtonGroup();
        goalButtonGroup.add(goalBlackRadioButton);
        goalButtonGroup.add(goalWhiteRadioButton);
        tabbedPane = new javax.swing.JTabbedPane();
        mainTextPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        currTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        goalTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        actionsTextArea = new javax.swing.JTextArea();
        workButton = new javax.swing.JButton();
        guiPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        currNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        currBlackRadioButton = new javax.swing.JRadioButton();
        currWhiteRadioButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        currOnTextField = new javax.swing.JTextField();
        currMakeButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        goalNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        goalBlackRadioButton = new javax.swing.JRadioButton();
        goalWhiteRadioButton = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        goalOnTextField = new javax.swing.JTextField();
        goalMakeButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        runMenu = new javax.swing.JMenu();
        workMenuItem = new javax.swing.JMenuItem();
        stepMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STRIPS");
        setBounds(new java.awt.Rectangle(220, 55, 0, 0));
        setResizable(false);

        mainTextPanel.setPreferredSize(new java.awt.Dimension(1071, 630));

        currTextArea.setColumns(20);
        currTextArea.setFont(new java.awt.Font("Times New Roman", 0, 13));
        currTextArea.setRows(5);
        currTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current State", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        jScrollPane1.setViewportView(currTextArea);
        currTextArea.getAccessibleContext().setAccessibleParent(mainTextPanel);

        goalTextArea.setColumns(20);
        goalTextArea.setFont(new java.awt.Font("Times New Roman", 0, 13));
        goalTextArea.setRows(5);
        goalTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Goal State", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        jScrollPane2.setViewportView(goalTextArea);
        goalTextArea.getAccessibleContext().setAccessibleParent(mainTextPanel);

        actionsTextArea.setColumns(20);
        actionsTextArea.setEditable(false);
        actionsTextArea.setFont(new java.awt.Font("Times New Roman", 0, 13));
        actionsTextArea.setRows(5);
        actionsTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        jScrollPane3.setViewportView(actionsTextArea);
        actionsTextArea.getAccessibleContext().setAccessibleParent(mainTextPanel);

        workButton.setText("WORK");
        workButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainTextPanelLayout = new javax.swing.GroupLayout(mainTextPanel);
        mainTextPanel.setLayout(mainTextPanelLayout);
        mainTextPanelLayout.setHorizontalGroup(
            mainTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainTextPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(workButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        mainTextPanelLayout.setVerticalGroup(
            mainTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(workButton)
                .addGap(51, 51, 51))
        );

        tabbedPane.addTab("Text mode", mainTextPanel);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel7.setText("Current State");

        jLabel1.setText("Name:");

        jLabel2.setText("Color");

        currBlackRadioButton.setText("Black");

        currWhiteRadioButton.setText("White");

        jLabel3.setText("On another block");

        currMakeButton.setText("Make");
        currMakeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currMakeButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel8.setText("Goal State");

        jLabel4.setText("Name:");

        jLabel5.setText("Color");

        goalBlackRadioButton.setText("Black");

        goalWhiteRadioButton.setText("White");

        jLabel6.setText("On another block");

        goalMakeButton.setText("Make");
        goalMakeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalMakeButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.setMaximumSize(new java.awt.Dimension(57, 23));
        okButton.setMinimumSize(new java.awt.Dimension(57, 23));
        okButton.setPreferredSize(new java.awt.Dimension(57, 23));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        stepButton.setText("STEP");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout guiPanelLayout = new javax.swing.GroupLayout(guiPanel);
        guiPanel.setLayout(guiPanelLayout);
        guiPanelLayout.setHorizontalGroup(
            guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
            .addGroup(guiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(992, Short.MAX_VALUE))
            .addGroup(guiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(guiPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(currNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2)
                        .addGroup(guiPanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(currWhiteRadioButton)
                                .addComponent(currBlackRadioButton)))
                        .addComponent(jLabel3)
                        .addComponent(currOnTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guiPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(currMakeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 922, Short.MAX_VALUE)
                        .addComponent(stepButton))
                    .addComponent(jLabel7))
                .addContainerGap())
            .addGroup(guiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(guiPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(goalNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5)
                        .addGroup(guiPanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(goalWhiteRadioButton)
                                .addComponent(goalBlackRadioButton)))
                        .addComponent(jLabel6)
                        .addComponent(goalOnTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guiPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(goalMakeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 919, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        guiPanelLayout.setVerticalGroup(
            guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(99, 99, 99)
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(currNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currBlackRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currWhiteRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currMakeButton)
                    .addComponent(stepButton))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(goalNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goalBlackRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goalWhiteRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goalOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(guiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goalMakeButton)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        tabbedPane.addTab("Graphical mode", guiPanel);
        guiPanel.add(currWorkspace).setBounds(140, 12, 821, 300);
        guiPanel.add(goalWorkspace).setBounds(140, 348, 821, 300);

        mainMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fileMenu.setText("File");

        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        runMenu.setText("Run");

        workMenuItem.setText("Work");
        workMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workMenuItemActionPerformed(evt);
            }
        });
        runMenu.add(workMenuItem);

        stepMenuItem.setText("Step");
        stepMenuItem.setEnabled(false);
        stepMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepMenuItemActionPerformed(evt);
            }
        });
        runMenu.add(stepMenuItem);

        mainMenuBar.add(runMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);
        helpMenu.add(jSeparator1);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        // clears all text areas
        currTextArea.setText("");
        goalTextArea.setText("");
        actionsTextArea.setText("");

        //clear all workspaces
        currWorkspace.clear();
        goalWorkspace.clear();

        clearGraphicsMode();

        // clear strips
        strips.clear();

        currWorkspace.repaint();
        goalWorkspace.repaint();
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        AboutDialog about = new AboutDialog(this, false);
        about.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        HelpDialog help = new HelpDialog(this, false);
        help.setVisible(true);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void workButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workButtonActionPerformed
        textWork();
    }//GEN-LAST:event_workButtonActionPerformed

    private void workMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workMenuItemActionPerformed
        if (mainTextPanel.isShowing()) {
            textWork();
        }
    }//GEN-LAST:event_workMenuItemActionPerformed

    private void stepMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepMenuItemActionPerformed
        if (guiPanel.isShowing()) {
            if (currMakeButton.isEnabled()) {
                currMakeButton.setEnabled(false);
                goalMakeButton.setEnabled(false);
            }
            graphicsWork();

        }
    }//GEN-LAST:event_stepMenuItemActionPerformed

    private void currMakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currMakeButtonActionPerformed
        if (currNameTextField.getText().equalsIgnoreCase("")
                || (!currBlackRadioButton.isSelected() && !currWhiteRadioButton.isSelected())
                || (currBlackRadioButton.isSelected() && currWhiteRadioButton.isSelected())
                || currBlockNames.contains(currNameTextField.getText())) {
            WarningGraphics warning = new WarningGraphics(this, false);
            warning.setVisible(true);
        } else {
            boolean on = !currOnTextField.getText().equalsIgnoreCase("");

            int[] param;
            param = currWorkspace.calculateParam(on, currOnTextField.getText());

            currWorkspace.addBlock(new Block(currNameTextField.getText(),
                    currBlackRadioButton.isSelected(), param[0], param[1]), on, currOnTextField.getText());

            currBlockNames.add(currNameTextField.getText());
        }
    }//GEN-LAST:event_currMakeButtonActionPerformed

    private void goalMakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalMakeButtonActionPerformed
        if (goalNameTextField.getText().equalsIgnoreCase("")
                || (!goalBlackRadioButton.isSelected() && !goalWhiteRadioButton.isSelected())
                || (goalBlackRadioButton.isSelected() && goalWhiteRadioButton.isSelected())
                || goalBlockNames.contains(goalNameTextField.getText())) {
            WarningGraphics warning = new WarningGraphics(this, false);
            warning.setVisible(true);
        } else {
            boolean on = !goalOnTextField.getText().equalsIgnoreCase("");

            int[] param;
            param = goalWorkspace.calculateParam(on, goalOnTextField.getText());

            goalWorkspace.addBlock(new Block(goalNameTextField.getText(),
                    goalBlackRadioButton.isSelected(), param[0], param[1]), on, goalOnTextField.getText());

            goalBlockNames.add(goalNameTextField.getText());
        }
    }//GEN-LAST:event_goalMakeButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        graphicsInitialize();

        stepButton.setEnabled(true);
        okButton.setEnabled(false);

        stepMenuItem.setEnabled(true);
    }//GEN-LAST:event_okButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        if (strips.isFulfilledGoal()) {
            stepButton.setEnabled(false);
            stepMenuItem.setEnabled(false);
            okButton.setEnabled(false);
        } else {
            graphicsWork();
        }
    }//GEN-LAST:event_stepButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    private void textWork() {
        String currState = currTextArea.getText(),
                goalState = goalTextArea.getText();
        if (currState.equalsIgnoreCase("") || goalState.equalsIgnoreCase("")) {
            // warning
            WarningText warning = new WarningText(this, false);
            warning.setVisible(true);
        } else {
            stripsInitialize(currState, goalState);

            strips.work();
            actionsTextArea.setText(strips.printPlan());
        }
    }

    private void stripsInitialize(String currState, String goalState) {
        if (strips == null) {
            // make an instance
            strips = new StripsLogic(goalState.split("\\n"), currState.split("\\n"));
        } else {
            // delete old goals
            strips.deleteOldGoals();

            // fill with new goals
            strips.fillCurrentState(currState.split("\\n"));
            strips.fillGoalStack(goalState.split("\\n"));
        }
    }

    private void graphicsInitialize() {
        String currState = currWorkspace.makeStringState(),
                goalState = goalWorkspace.makeStringState();

        stripsInitialize(currState, goalState);
    }

    private void graphicsWork() {

        // while not action is performed
        // do trivial/compose goal work
        while (!strips.singleStep());

        // when action is performed
        // recalculate block position
        // or color and repaint
        if (!strips.getGoalStack().empty()) {
            currWorkspace.recalculatePlaceOfBlock(strips);

            currWorkspace.repaint();
        }
    }

    private void clearGraphicsMode() {
        currBlackRadioButton.setSelected(false);
        currWhiteRadioButton.setSelected(false);
        currNameTextField.setText("");
        currOnTextField.setText("");
        currMakeButton.setEnabled(true);

        goalBlackRadioButton.setSelected(false);
        goalWhiteRadioButton.setSelected(false);
        goalNameTextField.setText("");
        goalOnTextField.setText("");
        goalMakeButton.setEnabled(true);

        okButton.setEnabled(true);
        stepButton.setEnabled(false);

        currBlockNames.clear();
        goalBlockNames.clear();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JTextArea actionsTextArea;
    private javax.swing.JRadioButton currBlackRadioButton;
    private javax.swing.ButtonGroup currButtonGroup;
    private javax.swing.JButton currMakeButton;
    private javax.swing.JTextField currNameTextField;
    private javax.swing.JTextField currOnTextField;
    private javax.swing.JTextArea currTextArea;
    private javax.swing.JRadioButton currWhiteRadioButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JRadioButton goalBlackRadioButton;
    private javax.swing.ButtonGroup goalButtonGroup;
    private javax.swing.JButton goalMakeButton;
    private javax.swing.JTextField goalNameTextField;
    private javax.swing.JTextField goalOnTextField;
    private javax.swing.JTextArea goalTextArea;
    private javax.swing.JRadioButton goalWhiteRadioButton;
    private javax.swing.JPanel guiPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainTextPanel;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JButton okButton;
    private javax.swing.JMenu runMenu;
    private javax.swing.JButton stepButton;
    private javax.swing.JMenuItem stepMenuItem;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JButton workButton;
    private javax.swing.JMenuItem workMenuItem;
    // End of variables declaration//GEN-END:variables
}
