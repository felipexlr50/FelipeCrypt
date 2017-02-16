package com.example.felipexlr.felipecrypt.core;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHACoding {
	


	public String shaCoding(String password, int typeCode) throws NoSuchAlgorithmException{
		String ret=null;

		MessageDigest md = MessageDigest.getInstance("SHA-"+String.valueOf(typeCode));
		md.update(password.getBytes());

		byte byteData[] = md.digest();


		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		ret=sb.toString();

		return ret;
	}

	public String toBinary(String password, int typeCode ) throws NoSuchAlgorithmException{

		MessageDigest md = MessageDigest.getInstance("SHA-"+String.valueOf(typeCode));
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		StringBuilder sb = new StringBuilder(byteData.length * Byte.SIZE);
		for( int i = 0; i < Byte.SIZE * byteData.length; i++ )
			sb.append((byteData[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		return sb.toString();
	}

	
	public int intSHA(String password, int typeCode){
		int ret=0;
		int aux;
		BigInteger big;
		try {
			big = new BigInteger(toBinary(password, typeCode), 2);
			aux = big.intValue();
			if(aux<0){
				aux*=-1;
			}
			ret=aux;
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} 
		
		return ret;
	}
	

}








