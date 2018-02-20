package UserUtilities;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

public class DataConceal {
	
	public String dataEncode(String data){
		
		return DatatypeConverter.printBase64Binary(data.getBytes());
	}
	
	public String dataDecode(String data){
			
		return new String(DatatypeConverter.parseBase64Binary(data));
	}
	
	
	/*public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "aashishnk";
        
        DataConceal de = new DataConceal();
        
        System.out.println(de.dataEncode("azadnaik@gmail.com"));
        System.out.println(de.dataDecode("YhphZG5haWtAZ21haWwuY29t"));
        

    }*/

}
