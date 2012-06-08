package ch.unibas.medizin.osce.client.a_nonroo.client.place;

import java.util.HashMap;
import java.util.Map;

import ch.unibas.medizin.osce.client.a_nonroo.client.ui.VisibleRange;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckRequest;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.requestfactory.shared.RequestFactory;

public class AnamnesisCheckPlace extends OsMaPlace {

	private final static OsceConstants constants = GWT
			.create(OsceConstants.class);

	private static final String SEPARATOR = "!";

	private static final int TOKEN_INDEX = 0;

	private static final int PAGESTART_INDEX = 1;

	private static final int PAGELEN_INDEX = 2;

	private static final int FILTER_TITLE_ID = 3;
	private static final int SEARCHSTR_INDEX = 4;

	public static final String DEFAULT_SEARCHSTR = constants.searchField();

	private String token = "";

	private int pageStart;

	private String pageLen = "10";

	private String filterTileId = "";

	private String searchStr = "";
	
	// holds  the ids of Checks that have been edited
	private Map<Long, Integer> orderEditedMap = new HashMap<Long, Integer>(); // TODO delete
	
	private AnamnesisCheckRequest req = null; 
	

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public String getPageLen() {
		return pageLen;
	}

	public void setPageLen(String pageLen) {
		this.pageLen = pageLen;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getFilterTileId() {
		return filterTileId;
	}

	public void setFilterTileId(String filterTileId) {
		this.filterTileId = filterTileId;
	}

	public AnamnesisCheckPlace() {
		this.token = "AnamnesisCheckPlace";

	}

	public AnamnesisCheckPlace(String token, int pageStart, String pageLen,
			String searchStr, String filterTitleId) {
		this.token = token;
		this.pageStart = pageStart;
		this.pageLen = pageLen;
		this.searchStr = searchStr;
		this.filterTileId = filterTitleId;

	}

	public AnamnesisCheckPlace(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Map<Long, Integer> getOrderEditedMap() {
		return orderEditedMap;
	}

	public void setOrderEditedMap(Map<Long, Integer> orderEditedMap) {
		this.orderEditedMap = orderEditedMap;
	}

	public AnamnesisCheckRequest getReq() {
		return req;
	}

	/**
	 * Tokenizer.
	 */

	public static class Tokenizer implements
			PlaceTokenizer<AnamnesisCheckPlace> {
		private final RequestFactory requests;

		public Tokenizer(RequestFactory requests) {
			Log.debug("AnamnesisCheckPlace.Tokenizer");
			this.requests = requests;
		}

		@Override
		public AnamnesisCheckPlace getPlace(String token) {

			String[] tokenList = token.split(SEPARATOR);

			int pageStartIdx = 0;
			String searchString = "";
			String pageLen = VisibleRange.TEN.getName(); // Includes token all
			String filterId = ""; // Includes token all

			if (tokenList.length > 2) {

				try {
					pageStartIdx = Integer.parseInt(tokenList[PAGESTART_INDEX]);

					searchString = tokenList[SEARCHSTR_INDEX];

					pageLen = tokenList[PAGELEN_INDEX];

					filterId = tokenList[FILTER_TITLE_ID];

				} catch (NumberFormatException nfe) {

				} catch (IndexOutOfBoundsException oob) {
					// User could manipulate the address causing a number format
					// exception or an array
					// Index out of bounds exception.

				}

			}

			return new AnamnesisCheckPlace(tokenList[TOKEN_INDEX],
					pageStartIdx, pageLen, searchString, filterId);
		}

		// public AnamnesisCheckPlace getPlace(String token, int pageStart,
		// String pageLen, String searchStr) {
		// Log.debug("AnamnesisCheckPlace.Tokenizer.getPlace");
		// return new AnamnesisCheckPlace(pageStart, pageLen, searchStr);
		// }

		public String getToken(AnamnesisCheckPlace place) {

			// return place.getToken();
			int pageStart = 0;
			String pageLen = "";
			String searchStr = "";

			if (place.getPageStart() != 0) {

				pageStart = place.getPageStart();
			}
			if (place.getPageLen().equals("")) {
				pageLen = VisibleRange.TEN.getName();
			} else {
				pageLen = place.getPageLen();
			}
			if (place.getSearchStr().equals("")) {
				searchStr ="";
			} else {
				searchStr = place.getSearchStr();
			}

			return place.getToken() + SEPARATOR + pageStart + SEPARATOR
					+ pageLen + SEPARATOR + place.getFilterTileId() + SEPARATOR
					+ searchStr;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageLen == null) ? 0 : pageLen.hashCode());
		result = prime * result + pageStart;
		result = prime * result
				+ ((searchStr == null) ? 0 : searchStr.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnamnesisCheckPlace other = (AnamnesisCheckPlace) obj;
		if (pageLen == null) {
			if (other.pageLen != null)
				return false;
		} else if (!pageLen.equals(other.pageLen))
			return false;
		if (pageStart != other.pageStart)
			return false;
		if (searchStr == null) {
			if (other.searchStr != null)
				return false;
		} else if (!searchStr.equals(other.searchStr))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnamnesisCheckPlace [token=" + token + ", pageStart="
				+ pageStart + ", pageLen=" + pageLen + ", searchStr="
				+ searchStr + "]";
	}

	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj) {
	// return true;
	// }
	// if (obj == null) {
	// return false;
	// }
	// if (getClass() != obj.getClass()) {
	// return false;
	// }
	//
	// return true;
	// }

}
