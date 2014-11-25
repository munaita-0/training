public class MyHashTable {
	
	/**
	 * �n�b�V���G���g���[
	 * @author suzuki_shogo
	 *
	 */
	public static class MyHashEntry<String, T> {
		private String key;
		private T value;
		
		public MyHashEntry (String key, T value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey () {
			return key;
		}
		
		public T getValue() {
			return value;
		}
	}
	
	/**
	 * �n�b�V���}�b�v
	 * @author suzuki_shogo
	 *
	 */
	public static class MyHashMap<String, T> {
		private final int ANY_INTEGER = 100;

		private MyHashEntry<String, T>[] hashEntryArray;
		private int maxLength = ANY_INTEGER;
		private int size = 0;
		
		public MyHashMap() {
			this.hashEntryArray = new MyHashEntry[maxLength];
		}
		
		/**
		 * @param key
		 * @param value
		 */
		public void put(String key, T value) {
			// �z��̃T�C�Y������Ȃ��̂ŐV�����z�����蒼��
			if (size >= maxLength) {
				maxLength += ANY_INTEGER;
				MyHashEntry<String, T>[] newHashEntryArray = new MyHashEntry[maxLength];
				for (int i = 0; i < hashEntryArray.length; i++) {
					newHashEntryArray[i] = hashEntryArray[i];
				}
				hashEntryArray = newHashEntryArray;
			}
			
			// �L�[���擾
			int useKey;
			try {
				useKey = getUseKey((java.lang.String) key, hashEntryArray, maxLength);
			} catch (Exception e) {
				return;	// �o�^�ł��Ȃ�
			}
			
			hashEntryArray[useKey] = new MyHashEntry<String, T>(key, value);
			++size;
		}
		
		/**
		 * @param key
		 * @return
		 */
		public Object get(String key) {
			int useKey = key.hashCode() % maxLength;
			
			if (hashEntryArray[useKey] == null) {
				return null;
			}
			
			try {
				useKey = getUseKey((java.lang.String) key, hashEntryArray, maxLength);
			} catch (Exception e) {
				return null;
			}
			
			return hashEntryArray[useKey].getValue();
		}
	}
	
	/**
	 * �n�b�V���e�[�u���̃L�[�ƂȂ鐮���l��Ԃ��܂�
	 * �L�[�̏d��������ꍇ��userKey + 1�̃L�[�œo�^���܂�
	 * ����ɏd��������ꍇ�͂�����J��Ԃ��܂�
	 * maxLengh�ȏ�񐔃`�F�b�N���s����ꍇ�̓G���[��Ԃ��܂�
	 * @param key
	 * @param hashEntryArray
	 * @param maxLength
	 * @return
	 * @throws Exception
	 */
	private static int getUseKey(String key, MyHashTable.MyHashEntry[] hashEntryArray, int maxLength) throws Exception {
		int useKey = key.hashCode() % maxLength;
		
		int count = 0;
		
		while (hashEntryArray[useKey] != null && !hashEntryArray[useKey].getKey().equals(key)) {
			useKey = (useKey + 1) % maxLength;
			++count;
			if (count > maxLength) {
				throw new Exception("cant find right one.");
			}
		}
		return useKey;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHashMap<String, String> a = new MyHashMap<String, String>();
		for (int i = 0; i < 200; i++) {
			a.put(String.valueOf(i), String.valueOf(i) + "_");
		}
		
		for (int i = 0; i < 200; i++) {
			System.out.println("value: " + a.get(String.valueOf(i)));
		}
		
		System.out.println(a.get("300"));
		
	}
}
