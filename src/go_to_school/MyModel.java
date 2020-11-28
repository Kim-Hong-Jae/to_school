package go_to_school;

import javax.swing.table.*;

public class MyModel extends AbstractTableModel{
	
	//TODO 서버에서 과목, 시간 받아와서 글 및 바탕색 설정
	
	public Object[][] TABLE_DATA = {
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
