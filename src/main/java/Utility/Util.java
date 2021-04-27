package Utility;

import java.util.ArrayList;
import java.util.Hashtable;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Util {

	
	public ArrayList<String> getTestData_SeqNos(String SheetName) throws FilloException
	{
		Fillo fillo=new Fillo();
		Connection connection = fillo.getConnection(".//Database//Datafile.xlsx");
		String DataQuery="SELECT * FROM "+SheetName+" Where  Control ='1'";
		Recordset Data_RS=connection.executeQuery(DataQuery);
		ArrayList<String> columns=new ArrayList<String>();
		
		while(Data_RS.next()) {
			columns.add(Data_RS.getField("Sl_No").toString());
		}
		connection.close();
		return columns;
		
	}
	
	public Hashtable<String,String> get_Data(String SheetName,String Seq_No) throws FilloException
	{
			Fillo fillo=new Fillo();
			Connection connection = fillo.getConnection(".//Database//Datafile.xlsx");
			Hashtable<String,String> data_values=new Hashtable<String,String>();
			String DataQuery="SELECT * FROM "+SheetName+" Where Sl_No='"+Seq_No+"'";
			Recordset Data_RS=connection.executeQuery(DataQuery);
			ArrayList<String> columns=new ArrayList<String>();
			columns=Data_RS.getFieldNames();
			//System.out.println("columns:"+columns);
			for(int i=1 ;i<columns.size();i++) {
				Data_RS.next();
				String columnName=columns.get(i);
				String columnValue=Data_RS.getField(columnName);
				data_values.put(columnName.trim().toString(), columnValue.trim().toString());
			
			}
			connection.close();
			return data_values;
	}
	
}
