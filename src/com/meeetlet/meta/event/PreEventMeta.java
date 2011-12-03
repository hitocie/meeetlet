package com.meeetlet.meta.event;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-12-03 14:03:24")
/** */
public final class PreEventMeta extends org.slim3.datastore.ModelMeta<com.meeetlet.model.event.PreEvent> {

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>> budgets = new org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>>(this, "budgets", "budgets", java.util.List.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.util.Date>, java.util.Date> dates = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.util.Date>, java.util.Date>(this, "dates", "dates", java.util.List.class);

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>> genres = new org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>>(this, "genres", "genres", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreEvent, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreEvent, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>> places = new org.slim3.datastore.StringCollectionAttributeMeta<com.meeetlet.model.event.PreEvent, java.util.List<java.lang.String>>(this, "places", "places", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreEvent, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreEvent, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final PreEventMeta slim3_singleton = new PreEventMeta();

    /**
     * @return the singleton
     */
    public static PreEventMeta get() {
       return slim3_singleton;
    }

    /** */
    public PreEventMeta() {
        super("PreEvent", com.meeetlet.model.event.PreEvent.class);
    }

    @Override
    public com.meeetlet.model.event.PreEvent entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.meeetlet.model.event.PreEvent model = new com.meeetlet.model.event.PreEvent();
        model.setBudgets(toList(java.lang.String.class, entity.getProperty("budgets")));
        model.setDates(toList(java.util.Date.class, entity.getProperty("dates")));
        model.setGenres(toList(java.lang.String.class, entity.getProperty("genres")));
        model.setKey(entity.getKey());
        model.setPlaces(toList(java.lang.String.class, entity.getProperty("places")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("budgets", m.getBudgets());
        entity.setProperty("dates", m.getDates());
        entity.setProperty("genres", m.getGenres());
        entity.setProperty("places", m.getPlaces());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
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
        com.meeetlet.model.event.PreEvent m = (com.meeetlet.model.event.PreEvent) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getBudgets() != null){
            writer.setNextPropertyName("budgets");
            writer.beginArray();
            for(java.lang.String v : m.getBudgets()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getDates() != null){
            writer.setNextPropertyName("dates");
            writer.beginArray();
            for(java.util.Date v : m.getDates()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getGenres() != null){
            writer.setNextPropertyName("genres");
            writer.beginArray();
            for(java.lang.String v : m.getGenres()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getPlaces() != null){
            writer.setNextPropertyName("places");
            writer.beginArray();
            for(java.lang.String v : m.getPlaces()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.meeetlet.model.event.PreEvent jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.meeetlet.model.event.PreEvent m = new com.meeetlet.model.event.PreEvent();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("budgets");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("budgets");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setBudgets(elements);
            }
        }
        reader = rootReader.newObjectReader("dates");
        {
            java.util.ArrayList<java.util.Date> elements = new java.util.ArrayList<java.util.Date>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("dates");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.util.Date v = decoder0.decode(reader, (java.util.Date)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setDates(elements);
            }
        }
        reader = rootReader.newObjectReader("genres");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("genres");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setGenres(elements);
            }
        }
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("places");
        {
            java.util.ArrayList<java.lang.String> elements = new java.util.ArrayList<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("places");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setPlaces(elements);
            }
        }
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}