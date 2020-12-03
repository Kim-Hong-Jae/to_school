package go_to_school;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;

public class MyModel extends AbstractTableModel{

	public static String[] columnNames = {"","월요일","화요일","수요일","목요일","금요일"};
	public static Object[][] TABLE_DATA = {
			{"09","","","","",""},
			{"10","","","","",""},
			{"11","","","","",""},
			{"12","","","","",""},
			{"13","","","","",""},
			{"14","","","","",""},
			{"15","","","","",""},
			{"16","","","","",""},
			{"17","","","","",""},
			{"18","","","","",""},
			{"19","","","","",""},
			{"20","","","","",""},
			{"21","","","","",""},
			{"22","","","","",""}
	    };

	
	public int getColumnCount() {
		return TABLE_DATA[0].length;
	}
	public int getRowCount() {
		return TABLE_DATA.length;
	}
	public Object getValueAt(int r, int c) {
		return TABLE_DATA[r][c];
	}
	public void setText(int r, int c, String s) {
		TABLE_DATA[r][c] = s;
	}
	
}
