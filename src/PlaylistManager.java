/**
 * This class represents the full media player system which is implemented as a singly linked list of Playlist objects.
 * 
 */
public class PlaylistManager {
	// These are the instance variables of the class.
	private SLLNode<Playlist> head;
	private int numPlaylists;

	// Thia is the constructor of the class and it initializes the instance variables. 
	public PlaylistManager() {
		this.head = null;
		this.numPlaylists = 0;
	}
	
	// This method adds a playlist to the end of the list otherwise throws an Exception if empty or a duplicate exists.
	public void addPlaylist(Playlist newPlaylist) throws PlayerException {
		if (newPlaylist == null) {
			throw new PlayerException("Playlist is empty");
		}
		for (SLLNode<Playlist> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getName().equals(newPlaylist.getName())) {
				throw new PlayerException("A playlist with this name already exists");
			}
		}
		SLLNode<Playlist> newNode = new SLLNode<>(newPlaylist);
		if (head == null) {
			head = newNode;
		} else {
			SLLNode<Playlist> tail = head;
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			tail.setNext(newNode);
		}
		numPlaylists++;
	}
	
	// This method removes and returns the Playlist with the specified name, otherwise throws an Exception if not found.
	public Playlist removePlaylist(String listName) throws PlayerException {
		if (head.getElement().getName().equals(listName)) {
			Playlist playlistRemoved = head.getElement();
			head = head.getNext();
			return playlistRemoved;
		}
		for (SLLNode<Playlist> current = head; current.getNext() != null; current = current.getNext()) {
			if (current.getNext().getElement().getName().equals(listName)) {
				Playlist playlistRemoved = current.getNext().getElement();
				current.setNext(current.getNext().getNext());
				return playlistRemoved;
			}
		}
		throw new PlayerException("No playlist was found with the name you were looking for");
	}
	
	// This method finds and returns the playlist with the specified name within the list, otherwise throws an Exception if not found. 
	public Playlist getPlaylist(String listName) throws PlayerException {
		for (SLLNode<Playlist> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getName().equals(listName)) {
				return current.getElement();
			}
		}
		throw new PlayerException("No playlist was found with the name you were looking for");
	}
	
	// This method adds a MediaFile to the playlist and throws an Exception if the playlist is missing or is a duplicate.
	public void addMediaToPlaylist(String playlistName, MediaFile media) throws PlayerException {
		boolean playlistFound = false;
		for (SLLNode<Playlist> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getName().equals(playlistName)) {
				Playlist playlist = current.getElement();
				playlist.addMedia(media);
				playlistFound = true;
				break;
			}
		}
		if (!playlistFound) {
			throw new PlayerException("No playlist was found with the name you were looking for");
		}
	}
	
	// This method removes and returns the specified MediaFile from the playlist and throws an Exception if the playlist or the MediaFile is missing. 
	public MediaFile removeMediaFromPlaylist(String playlistName, String mediaTitle) throws PlayerException {
		for (SLLNode<Playlist> current = head; current != null; current = current.getNext()) {
			if (current.getElement().getName().equals(playlistName)) {
				return current.getElement().removeMedia(mediaTitle);
			}
		}
		throw new PlayerException("No playlist was found with the name you were looking for");
	}
	
	// This method returns a formatted string following a specific format.
	public String toString() {
		String format = "Playlist Manager:\n";
		for (SLLNode<Playlist> current = head; current != null; current = current.getNext()) {
			format += current.getElement().toString() + "\n";
		}
		return format;
	}
}