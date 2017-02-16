package com.example.felipexlr.felipecrypt.core;

public class EncryptEngine {

	/**
	 * Codifica cada char de uma string adicionando a chave(key) a cada char.
	 * Encrypt with char from the string adding the key for with char.
	 * @param text
	 * @param key
	 * @return
	 *
	 */
	
	private final int UNICODE_LIMIT = 65533;	//Limite da tabela unicode
	private SHACoding sha = new SHACoding();
	
	public String encode(String text,int key){

		String result = "";
		int tempKey;

		for(int i = 0;i<text.length();i++){

			if(i%2==0){
				tempKey = text.charAt(i)+key*i;
			}
			else{
				tempKey = text.charAt(i)+key;
			}
			
			//cria o loop dos caracteres quando a tempKey ultrapassa o limite do unicode
			if(tempKey>UNICODE_LIMIT){
				do{
					tempKey=tempKey-UNICODE_LIMIT;
				}
				while(tempKey>UNICODE_LIMIT);
			}
			
			if(tempKey<0){
				do{
				tempKey=tempKey+UNICODE_LIMIT;
				}
				while(tempKey<0);
			}

			result += (char)tempKey;
			//System.out.println(tempKey);
		}
		
		return result;

	}


	/**
	 * Decodifica cada char de uma string subtraindo a chave(key) a cada char.
	 * Decrypt with char from the string subtracting the key for with char.
	 * @param text
	 * @param key
	 * @return
	 *
	 */
	public String decode(String text, int key){
		String result = "";
		int tempKey;
		for(int i = 0;i<text.length();i++){
			if(i%2==0){
				tempKey = text.charAt(i)-key*i;
			}
			else{
				tempKey = text.charAt(i)-key;
			}
			
			
			if(tempKey<0){
				do{
				tempKey=tempKey+UNICODE_LIMIT;
				}
				while(tempKey<0);
			}
			
			if(tempKey>UNICODE_LIMIT){
				do{
					tempKey=tempKey-UNICODE_LIMIT;
				}
				while(tempKey>UNICODE_LIMIT);
			}
			
			result += (char)tempKey;
			


		}

		return result;

	}
	
	
	public int keyEncrypt(String password){
		int ret=0;
		int sum = 0;
		for(int i =0;i<password.length();i++){
			sum += password.charAt(i);
					
		}
		ret = (int)(Math.log10(sum*sum)*sum/10+sum);
		
		if(ret<0){
			ret *=-1;
		}
		
		return  ret;
	}
	
	public int keyEncrypt2(String password){
		int ret=0;
		int aux = sha.intSHA(password, 512);
		//System.out.println(aux);
		
		ret = aux;
		//System.out.println(ret);
		return  ret;
	}
	
	
	
	
}
