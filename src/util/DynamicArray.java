/*
 * 
 * Author: Jason Lai
 * 
 */


package util;

public class DynamicArray<T> {
	
	private final int INIT_CAP = 30;
	private final int INVALID = -1;
	private Object[] arr;
	private int numObj;
	private int maxCap;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public DynamicArray() {
		arr = new Object[INIT_CAP];	
		numObj = 0;
		maxCap = INIT_CAP;
		
		for (int i = 0; i < maxCap; i++) {
			arr[i] = new Object();
		}
	}
	
	public DynamicArray(T[] newArr) {				
		if (newArr.length > INIT_CAP) {
			maxCap = numObj * 2;
		}
		else {
			maxCap = INIT_CAP;
		}
		
		arr = new Object[maxCap];	
		numObj = newArr.length;
		
		for (int i = 0; i < newArr.length; i++) {
			arr[i] = newArr[i];
		}
	}
	
	public DynamicArray(DynamicArray<T> copy) {
		arr = copy.toArray();
		numObj = copy.getSize();
		maxCap = copy.getMaxSize();
	}
	
	/*
	 * 
	 * PUBLIC MEMBER FUNCTIONS
	 * 
	 */
	
	public boolean isEmpty() {
		return numObj == 0;
	}
	
	public int search(T obj) {
		int index = -1;
		
		for (int i = 0; i < numObj; i++) {
			if (obj.equals(arr[i])) {
				index = i;
				break;
			}
		}
		
		return index;		
	}
	
	public T get(int i) {
		@SuppressWarnings("unchecked")
		final T obj = (T) arr[i];
		return obj;
	}
	
	public int getSize() {
		return numObj;
	}
	
	public int getMaxSize() {
		return maxCap;
	}
	
	public void add(T obj) {
		add(obj, numObj);
	}
	
	public void add(T obj, int index) {
		if (index <= INVALID) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		
		if (index < maxCap) {
			arr[index] = obj;
		}
		
		else {
			resize(maxCap * 2);
			arr[index] = obj;
		}
		
		numObj++;
	}
	
	@SuppressWarnings("unchecked")
	public void addPushBack(T obj, int index) {
		Object[] temp = {obj};
		addPushBack((T[]) temp, index);
	}
	
	public void addPushBack(T[] obj, int index) {
		
		int arrlen = obj.length;
		int newsize = numObj + arrlen;
		
		if (newsize > maxCap) {
			resize(newsize);
		}
		
		Object[] newarr = new Object[newsize];
		
		for (int i = 0; i < index; i++) {
			newarr[i] = arr[i];		
		}
		
		for (int i = index; i < arrlen; i++) {
			newarr[i] = obj[i - index];
		}
		
		for (int i = index + arrlen; i < newsize; i++) {
			newarr[i] = arr[i - arrlen];
		}
		
		arr = newarr;
		
	}
	
	public void remove(int index) {
		if (index <= INVALID) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		
		else {
			arr[index] = null;
		}
		numObj--;
	}
	
	public void remove(T obj) {
		int index = search(obj);
		remove(index);
	}
	
	public void removePushBack(int index) {
		remove(index);
		for (int i = index; i < numObj - 1; i++) {
			arr[i] = arr[i + 1];
		}
		
		if (numObj == maxCap) {
			arr[numObj - 1] = null;
		}
		else {
			arr[numObj - 1] = arr[numObj];
		}
	}
	
	public void removePushBack(T obj) {
		int index = search(obj);
		removePushBack(index);
	}
	
	/*
	 * This function "merges" multiple elements within the DynamicArray
	 * with the given object obj in range [beginIndex, endIndex)
	 */
	public void merge(T obj, int beginIndex, int endIndex) {
		int numTrash = endIndex - beginIndex - 1;
		int tempEndIndex = endIndex;
		
		for (int i = beginIndex + 1; i < numObj; i++) {
			arr[i] = arr[tempEndIndex];
			tempEndIndex++;
		}
		
		arr[beginIndex] = obj;
		
		int terminationPt = numObj - numTrash - 1;
		for (int i = numObj - 1; i > terminationPt; i--) {
			arr[i] = null;
		}
		
		numObj -= numTrash;
	}
	
	public void clear() {
		arr = new Object[INIT_CAP];
		numObj = 0;
		maxCap = INIT_CAP;
	}
	
	public DynamicArray<T> clone() {
		DynamicArray<T> clone = new DynamicArray<T>(this);
		return clone;
	}
	
	public void resize(int size) {
		maxCap = size;
		Object[] temp = new Object[maxCap];
		
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		
		arr = temp;
	}
	
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		return (T[]) arr;
	}
	
	public String toString() {
		String str = "";
		
		for (int i = 0; i < numObj; i++) {
			str += (arr[i].toString() + "\n");
		}
		
		return str;
	}
	
	
}
