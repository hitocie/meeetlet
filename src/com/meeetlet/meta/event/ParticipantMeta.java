package com.meeetlet.meta.event;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-11-10 00:00:32")
/** */
public final class ParticipantMeta extends org.slim3.datastore.ModelMeta<com.meeetlet.model.event.Participant> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Participant> comment = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Participant>(this, "comment", "comment");

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Participant, org.slim3.datastore.ModelRef<com.meeetlet.model.event.Event>, com.meeetlet.model.event.Event> eventRef = new org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Participant, org.slim3.datastore.ModelRef<com.meeetlet.model.event.Event>, com.meeetlet.model.event.Event>(this, "eventRef", "eventRef", org.slim3.datastore.ModelRef.class, com.meeetlet.model.event.Event.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Participant, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Participant, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Participant, org.slim3.datastore.ModelRef<com.meeetlet.model.common.User>, com.meeetlet.model.common.User> userRef = new org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Participant, org.slim3.datastore.ModelRef<com.meeetlet.model.common.User>, com.meeetlet.model.common.User>(this, "userRef", "userRef", org.slim3.datastore.ModelRef.class, com.meeetlet.model.common.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Participant, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Participant, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ParticipantMeta slim3_singleton = new ParticipantMeta();

    /**
     * @return the singleton
     */
    public static ParticipantMeta get() {
       return slim3_singleton;
    }

    /** */
    public ParticipantMeta() {
        super("Participant", com.meeetlet.model.event.Participant.class);
    }

    @Override
    public com.meeetlet.model.event.Participant entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.meeetlet.model.event.Participant model = new com.meeetlet.model.event.Participant();
        model.setComment((java.lang.String) entity.getProperty("comment"));
        if (model.getEventRef() == null) {
            throw new NullPointerException("The property(eventRef) is null.");
        }
        model.getEventRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("eventRef"));
        model.setKey(entity.getKey());
        if (model.getUserRef() == null) {
            throw new NullPointerException("The property(userRef) is null.");
        }
        model.getUserRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("userRef"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("comment", m.getComment());
        if (m.getEventRef() == null) {
            throw new NullPointerException("The property(eventRef) must not be null.");
        }
        entity.setProperty("eventRef", m.getEventRef().getKey());
        if (m.getUserRef() == null) {
            throw new NullPointerException("The property(userRef) must not be null.");
        }
        entity.setProperty("userRef", m.getUserRef().getKey());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        if (m.getEventRef() == null) {
            throw new NullPointerException("The property(eventRef) must not be null.");
        }
        m.getEventRef().assignKeyIfNecessary(ds);
        if (m.getUserRef() == null) {
            throw new NullPointerException("The property(userRef) must not be null.");
        }
        m.getUserRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.meeetlet.model.event.Participant m = (com.meeetlet.model.event.Participant) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getComment() != null){
            writer.setNextPropertyName("comment");
            encoder0.encode(writer, m.getComment());
        }
        if(m.getEventRef() != null && m.getEventRef().getKey() != null){
            writer.setNextPropertyName("eventRef");
            encoder0.encode(writer, m.getEventRef(), maxDepth, currentDepth);
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getUserRef() != null && m.getUserRef().getKey() != null){
            writer.setNextPropertyName("userRef");
            encoder0.encode(writer, m.getUserRef(), maxDepth, currentDepth);
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.meeetlet.model.event.Participant jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.meeetlet.model.event.Participant m = new com.meeetlet.model.event.Participant();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("comment");
        m.setComment(decoder0.decode(reader, m.getComment()));
        reader = rootReader.newObjectReader("eventRef");
        decoder0.decode(reader, m.getEventRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("userRef");
        decoder0.decode(reader, m.getUserRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}