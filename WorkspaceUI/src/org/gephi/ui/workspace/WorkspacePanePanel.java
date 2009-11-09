/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.ui.workspace;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.UIManager;
import org.gephi.project.api.ProjectController;
import org.gephi.ui.components.CloseButton;
import org.gephi.workspace.api.Workspace;
import org.gephi.workspace.api.WorkspaceInformation;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Mathieu Bastian
 */
public final class WorkspacePanePanel extends javax.swing.JPanel implements MouseListener {

    private Workspace workspace;

    public WorkspacePanePanel(Workspace workspace) {
        this.workspace = workspace;
        initComponents();
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //detailsLabel.setFont(detailsLabel.getFont().deriveFont((float) (detailsLabel.getFont().getSize() - 2)));
        closeButton.setAction(new DeleteAction());
        closeButton.setCursor(Cursor.getDefaultCursor());

        //Workspace info
        WorkspaceInformation info = workspace.getLookup().lookup(WorkspaceInformation.class);
        workspaceLabel.setText(info.getName());
        detailsLabel.setText(info.getSource());

        //Selected
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        boolean selected = pc.getCurrentWorkspace() == workspace;
        if (selected) {
            //setBackground(UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
            workspaceLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        } else {
            //setBackground(UIManager.getDefaults().getColor("ComboBox.background"));
            workspaceLabel.setFont(new java.awt.Font("Tahoma", 0, 11));
        }
        setBackground(UIManager.getDefaults().getColor("ComboBox.background"));
        addMouseListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workspaceLabel = new javax.swing.JLabel();
        detailsLabel = new javax.swing.JLabel();
        closeButton = new CloseButton();

        workspaceLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        workspaceLabel.setText(org.openide.util.NbBundle.getMessage(WorkspacePanePanel.class, "WorkspacePanePanel.workspaceLabel.text")); // NOI18N

        detailsLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        detailsLabel.setForeground(new java.awt.Color(153, 153, 153));
        detailsLabel.setText(org.openide.util.NbBundle.getMessage(WorkspacePanePanel.class, "WorkspacePanePanel.detailsLabel.text")); // NOI18N

        closeButton.setToolTipText(org.openide.util.NbBundle.getMessage(WorkspacePanePanel.class, "WorkspacePanePanel.closeButton.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(detailsLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workspaceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(workspaceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detailsLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(closeButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JLabel workspaceLabel;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        if (pc.getCurrentWorkspace() != workspace) {
            pc.openWorkspace(workspace);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        setBackground(UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
    }

    public void mouseExited(MouseEvent e) {
        setBackground(UIManager.getDefaults().getColor("ComboBox.background"));
    }
    // End of variables declaration

    private class DeleteAction extends AbstractAction {

        public void actionPerformed(ActionEvent actionEvent) {
            String message = NbBundle.getMessage(WorkspacePanePanel.class, "WorkspacePanePanel_closeWorkspace_Question");
            String title = NbBundle.getMessage(WorkspacePanePanel.class, "WorkspacePanePanel_closeWorkspace_Title");
            NotifyDescriptor dd = new NotifyDescriptor(message, title,
                    NotifyDescriptor.YES_NO_OPTION,
                    NotifyDescriptor.QUESTION_MESSAGE, null, null);
            Object retType = DialogDisplayer.getDefault().notify(dd);
            if (retType == NotifyDescriptor.YES_OPTION) {
                ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
                pc.deleteWorkspace(workspace);
            }
        }
    }
}
