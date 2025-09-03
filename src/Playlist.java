/**
 * This class represents a playlist that is structured as a doubly linked list of MediaFile objects.
 * 
 */
public class Playlist {
	// These are the instance variables of the class.
	private String name;
	private DLLNode<MediaFile> head;
	private DLLNode<MediaFile> tail;
	private int size;
	
	/**
	 * This is the constructor of the class and it initializes the instance variables. 
	 * 
	 * @param name
	 */
	public Playlist(String name) {
		this.name = name;
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// This method adds a MediaFile to the end of playlist or throws an exception if empty or a duplicate exists.
	public void addMedia(MediaFile media) throws PlayerException {
		if (media == null) {
			throw new PlayerException("The media file is empty");
		}
		for (DLLNode<MediaFile> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getTitle().equals(media.getTitle())) {
				throw new PlayerException("A media file with this same name already exists");
			}
		}
		DLLNode<MediaFile> newNode = new DLLNode<>(media);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
		size++;
	}
	
	// This method removes the MediaFile with the specified title and returns that removed file otherwise throws an exception if not found.
	public MediaFile removeMedia(String title) throws PlayerException {
		for (DLLNode<MediaFile> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getTitle().equals(title)) {
				if (current == head) {
					head = current.getNext();
				}
				if (current == tail) {
					tail = current.getPrevious();
				}
				if (current.getPrevious() != null) {
					current.getPrevious().setNext(current.getNext());
				}
				if (current.getNext() != null) {
					current.getNext().setPrevious(current.getPrevious());
				}
				size--;
				return current.getElement();
			}
		}
		throw new PlayerException("No media file was found with this title");
	}
	
	// This method returns the MediaFile after the given title otherwise returns it at the first if empty or at the end, throws an exception if not found.
	public MediaFile getNextMedia(String currentTitle) throws PlayerException {
		if (head == null) {
			throw new PlayerException("Playlist is empty");
		}
		if (currentTitle == null) {
			return head.getElement();
		}
		for (DLLNode<MediaFile> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
			if (currentNode.getElement().getTitle().equals(currentTitle)) {
				if (currentNode.getNext() != null) {
					return currentNode.getNext().getElement();
				} else {
					return head.getElement();
				}
			}
		}
		throw new PlayerException("No media file with this name was found within this playlist");
	}
	
	// This method returns the MediaFile before the specified given title or at the last if empty or at the start, throws an exception if not found.
	public MediaFile getPreviousMedia(String currentTitle) throws PlayerException {
		if (head == null) {
			throw new PlayerException("Playlist is empty");
		}
		if (currentTitle == null) {
			return tail.getElement();
		}
		for (DLLNode<MediaFile> currentNode = tail; currentNode != null; currentNode = currentNode.getPrevious()) {
			if (currentNode.getElement().getTitle().equals(currentTitle)) {
				if (currentNode.getPrevious() != null) {
					return currentNode.getPrevious().getElement();
				} else {
					return tail.getElement();
				}
			}
		}
		throw new PlayerException("No media file with this name was found within the playlist");
	}
	
	// This method returns the number of nodes within the doubly linked list.
	public int getSize() {
		return size;
	}
	
	// This method returns the instance variable name.
	public String getName() {
		return name;
	}
	
	// This method returns a formatted string following a specific format.
	public String toString() {
		String format = "Playlist: " + name + "\n";
		int index = 1;
		for (DLLNode<MediaFile> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
			MediaFile mediaFile = currentNode.getElement();
			format += index + ". " + mediaFile.toString() + "\n";
			index++;
		}
		return format;
	}
}