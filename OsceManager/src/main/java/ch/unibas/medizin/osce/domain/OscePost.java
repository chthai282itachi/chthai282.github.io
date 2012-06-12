package ch.unibas.medizin.osce.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import ch.unibas.medizin.osce.domain.RoleTopic;
import javax.persistence.ManyToOne;
import java.util.Set;
import ch.unibas.medizin.osce.domain.OscePostRoom;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class OscePost {

    private Boolean isPossibleStart;

    @ManyToOne
    private RoleTopic roleTopic;

    @ManyToOne
    private ch.unibas.medizin.osce.domain.OscePost nextPost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oscePost")
    private Set<OscePostRoom> oscePostRooms = new HashSet<OscePostRoom>();
}
