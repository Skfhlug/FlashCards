package edu.team.flashcard;

import edu.team.flashcard.flashcard;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class flashcardApplication extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(flashcard.class );
        return h;
    }
}
