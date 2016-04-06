/**
 * A class for storing codes
 * 
 * @author zrjones
 *
 */
public class Codes {
	private Code[] codes;
	
	/**
	 * This gives back an object with a maximum of 10 codes
	 */
	public Codes() {
		this.codes = new Code[10];
		for (int i = 0; i < codes.length; i++) {
			codes[i] = new Code();
		}
	}
	
	/**
	 * Sets a code in the code array
	 * 
	 * @param numCode index of the code
	 * @param code the code to set
	 */
	public void setACode(int numCode, byte[] code) {
		codes[numCode-1].setCode(code);
	}
	
	/**
	 * Gets a code in the code array
	 * 
	 * @param numCode index of the code
	 * @return code at the index
	 */
	public byte[] getACode(int numCode) {
		return codes[numCode-1].getCode();
	}

}
