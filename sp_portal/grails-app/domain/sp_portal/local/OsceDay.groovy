package sp_portal.local
import local.*;

class OsceDay {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
    Integer version
    Date osceDate


    static constraints = {
		osceDate nullable: false
    }
	
	public String toString(){
		
		return osceDate.format("yyyy-MM-dd");
	}
	
}