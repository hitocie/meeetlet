package com.meeetlet.meta.event;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-01-09 10:44:09")
/** */
public final class EventMeta extends org.slim3.datastore.ModelMeta<com.meeetlet.model.event.Event> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> budget = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "budget", "budget");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.lang.Boolean> canceled = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.lang.Boolean>(this, "canceled", "canceled", boolean.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> comment = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "comment", "comment");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.util.Date> eventDate = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.util.Date>(this, "eventDate", "eventDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> eventid = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "eventid", "eventid");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> genre = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "genre", "genre");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Event, org.slim3.datastore.ModelRef<com.meeetlet.model.common.User>, com.meeetlet.model.common.User> ownerRef = new org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Event, org.slim3.datastore.ModelRef<com.meeetlet.model.common.User>, com.meeetlet.model.common.User>(this, "ownerRef", "ownerRef", org.slim3.datastore.ModelRef.class, com.meeetlet.model.common.User.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> place = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "place", "place");

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Event, org.slim3.datastore.ModelRef<com.meeetlet.model.event.PreEvent>, com.meeetlet.model.event.PreEvent> preEventRef = new org.slim3.datastore.ModelRefAttributeMeta<com.meeetlet.model.event.Event, org.slim3.datastore.ModelRef<com.meeetlet.model.event.PreEvent>, com.meeetlet.model.event.PreEvent>(this, "preEventRef", "preEventRef", org.slim3.datastore.ModelRef.class, com.meeetlet.model.event.PreEvent.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> shop = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "shop", "shop");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.util.Date> timestamp = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.util.Date>(this, "timestamp", "timestamp", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event> title = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.Event>(this, "title", "title");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.Event, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final EventMeta slim3_singleton = new EventMeta();

    /**
     * @return the singleton
     */
    public static EventMeta get() {
       return slim3_singleton;
    }

    /** */
    public EventMeta() {
        super("Event", com.meeetlet.model.event.Event.class);
    }

    @Override
    public com.meeetlet.model.event.Event entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.meeetlet.model.event.Event model = new com.meeetlet.model.event.Event();
        model.setBudget((java.lang.String) entity.getProperty("budget"));
        model.setCanceled(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("canceled")));
        model.setComment((java.lang.String) entity.getProperty("comment"));
        model.setEventDate((java.util.Date) entity.getProperty("eventDate"));
        model.setEventid((java.lang.String) entity.getProperty("eventid"));
        model.setGenre((java.lang.String) entity.getProperty("genre"));
        model.setKey(entity.getKey());
        if (model.getOwnerRef() == null) {
            throw new NullPointerException("The property(ownerRef) is null.");
        }
        model.getOwnerRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("ownerRef"));
        model.setPlace((java.lang.String) entity.getProperty("place"));
        if (model.getPreEventRef() == null) {
            throw new NullPointerException("The property(preEventRef) is null.");
        }
        model.getPreEventRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("preEventRef"));
        model.setShop((java.lang.String) entity.getProperty("shop"));
        model.setTimestamp((java.util.Date) entity.getProperty("timestamp"));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("budget", m.getBudget());
        entity.setProperty("canceled", m.isCanceled());
        entity.setProperty("comment", m.getComment());
        entity.setProperty("eventDate", m.getEventDate());
        entity.setProperty("eventid", m.getEventid());
        entity.setProperty("genre", m.getGenre());
        if (m.getOwnerRef() == null) {
            throw new NullPointerException("The property(ownerRef) must not be null.");
        }
        entity.setProperty("ownerRef", m.getOwnerRef().getKey());
        entity.setProperty("place", m.getPlace());
        if (m.getPreEventRef() == null) {
            throw new NullPointerException("The property(preEventRef) must not be null.");
        }
        entity.setProperty("preEventRef", m.getPreEventRef().getKey());
        entity.setProperty("shop", m.getShop());
        entity.setProperty("timestamp", m.getTimestamp());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        if (m.getOwnerRef() == null) {
            throw new NullPointerException("The property(ownerRef) must not be null.");
        }
        m.getOwnerRef().assignKeyIfNecessary(ds);
        if (m.getPreEventRef() == null) {
            throw new NullPointerException("The property(preEventRef) must not be null.");
        }
        m.getPreEventRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
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
        com.meeetlet.model.event.Event m = (com.meeetlet.model.event.Event) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getBudget() != null){
            writer.setNextPropertyName("budget");
            encoder0.encode(writer, m.getBudget());
        }
        writer.setNextPropertyName("canceled");
        encoder0.encode(writer, m.isCanceled());
        if(m.getComment() != null){
            writer.setNextPropertyName("comment");
            encoder0.encode(writer, m.getComment());
        }
        if(m.getEventDate() != null){
            writer.setNextPropertyName("eventDate");
            encoder0.encode(writer, m.getEventDate());
        }
        if(m.getEventid() != null){
            writer.setNextPropertyName("eventid");
            encoder0.encode(writer, m.getEventid());
        }
        if(m.getGenre() != null){
            writer.setNextPropertyName("genre");
            encoder0.encode(writer, m.getGenre());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getOwnerRef() != null && m.getOwnerRef().getKey() != null){
            writer.setNextPropertyName("ownerRef");
            encoder0.encode(writer, m.getOwnerRef(), maxDepth, currentDepth);
        }
        if(m.getParticipantsRef() != null){
            writer.setNextPropertyName("participantsRef");
            encoder0.encode(writer, m.getParticipantsRef());
        }
        if(m.getPlace() != null){
            writer.setNextPropertyName("place");
            encoder0.encode(writer, m.getPlace());
        }
        if(m.getPreEventRef() != null && m.getPreEventRef().getKey() != null){
            writer.setNextPropertyName("preEventRef");
            encoder0.encode(writer, m.getPreEventRef(), maxDepth, currentDepth);
        }
        if(m.getShop() != null){
            writer.setNextPropertyName("shop");
            encoder0.encode(writer, m.getShop());
        }
        if(m.getTimestamp() != null){
            writer.setNextPropertyName("timestamp");
            encoder0.encode(writer, m.getTimestamp());
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.meeetlet.model.event.Event jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.meeetlet.model.event.Event m = new com.meeetlet.model.event.Event();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("budget");
        m.setBudget(decoder0.decode(reader, m.getBudget()));
        reader = rootReader.newObjectReader("canceled");
        m.setCanceled(decoder0.decode(reader, m.isCanceled()));
        reader = rootReader.newObjectReader("comment");
        m.setComment(decoder0.decode(reader, m.getComment()));
        reader = rootReader.newObjectReader("eventDate");
        m.setEventDate(decoder0.decode(reader, m.getEventDate()));
        reader = rootReader.newObjectReader("eventid");
        m.setEventid(decoder0.decode(reader, m.getEventid()));
        reader = rootReader.newObjectReader("genre");
        m.setGenre(decoder0.decode(reader, m.getGenre()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("ownerRef");
        decoder0.decode(reader, m.getOwnerRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("participantsRef");
        reader = rootReader.newObjectReader("place");
        m.setPlace(decoder0.decode(reader, m.getPlace()));
        reader = rootReader.newObjectReader("preEventRef");
        decoder0.decode(reader, m.getPreEventRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("shop");
        m.setShop(decoder0.decode(reader, m.getShop()));
        reader = rootReader.newObjectReader("timestamp");
        m.setTimestamp(decoder0.decode(reader, m.getTimestamp()));
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}