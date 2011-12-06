package com.meeetlet.meta.event;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-12-06 23:59:52")
/** */
public final class PreParticipantMeta extends org.slim3.datastore.ModelMeta<com.meeetlet.model.event.PreParticipant> {

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response> budgets = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response>(this, "budgets", "budgets", java.util.List.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.PreParticipant> comment = new org.slim3.datastore.StringAttributeMeta<com.meeetlet.model.event.PreParticipant>(this, "comment", "comment");

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response> dates = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response>(this, "dates", "dates", java.util.List.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response> genres = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response>(this, "genres", "genres", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreParticipant, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreParticipant, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response> places = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response>(this, "places", "places", java.util.List.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response> shops = new org.slim3.datastore.CollectionAttributeMeta<com.meeetlet.model.event.PreParticipant, java.util.List<com.meeetlet.common.event.Response>, com.meeetlet.common.event.Response>(this, "shops", "shops", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreParticipant, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.meeetlet.model.event.PreParticipant, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final PreParticipantMeta slim3_singleton = new PreParticipantMeta();

    /**
     * @return the singleton
     */
    public static PreParticipantMeta get() {
       return slim3_singleton;
    }

    /** */
    public PreParticipantMeta() {
        super("PreParticipant", com.meeetlet.model.event.PreParticipant.class);
    }

    @Override
    public com.meeetlet.model.event.PreParticipant entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.meeetlet.model.event.PreParticipant model = new com.meeetlet.model.event.PreParticipant();
        model.setBudgets(stringListToEnumList(com.meeetlet.common.event.Response.class, entity.getProperty("budgets")));
        model.setComment((java.lang.String) entity.getProperty("comment"));
        model.setDates(stringListToEnumList(com.meeetlet.common.event.Response.class, entity.getProperty("dates")));
        model.setGenres(stringListToEnumList(com.meeetlet.common.event.Response.class, entity.getProperty("genres")));
        model.setKey(entity.getKey());
        model.setPlaces(stringListToEnumList(com.meeetlet.common.event.Response.class, entity.getProperty("places")));
        model.setShops(stringListToEnumList(com.meeetlet.common.event.Response.class, entity.getProperty("shops")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("budgets", enumListToStringList(m.getBudgets()));
        entity.setProperty("comment", m.getComment());
        entity.setProperty("dates", enumListToStringList(m.getDates()));
        entity.setProperty("genres", enumListToStringList(m.getGenres()));
        entity.setProperty("places", enumListToStringList(m.getPlaces()));
        entity.setProperty("shops", enumListToStringList(m.getShops()));
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
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
        com.meeetlet.model.event.PreParticipant m = (com.meeetlet.model.event.PreParticipant) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getBudgets() != null){
            writer.setNextPropertyName("budgets");
            writer.beginArray();
            for(com.meeetlet.common.event.Response v : m.getBudgets()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getComment() != null){
            writer.setNextPropertyName("comment");
            encoder0.encode(writer, m.getComment());
        }
        if(m.getDates() != null){
            writer.setNextPropertyName("dates");
            writer.beginArray();
            for(com.meeetlet.common.event.Response v : m.getDates()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getGenres() != null){
            writer.setNextPropertyName("genres");
            writer.beginArray();
            for(com.meeetlet.common.event.Response v : m.getGenres()){
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
            for(com.meeetlet.common.event.Response v : m.getPlaces()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getShops() != null){
            writer.setNextPropertyName("shops");
            writer.beginArray();
            for(com.meeetlet.common.event.Response v : m.getShops()){
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
    protected com.meeetlet.model.event.PreParticipant jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.meeetlet.model.event.PreParticipant m = new com.meeetlet.model.event.PreParticipant();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("budgets");
        {
            java.util.ArrayList<com.meeetlet.common.event.Response> elements = new java.util.ArrayList<com.meeetlet.common.event.Response>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("budgets");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.meeetlet.common.event.Response)null, com.meeetlet.common.event.Response.class));
                }
                m.setBudgets(elements);
            }
        }
        reader = rootReader.newObjectReader("comment");
        m.setComment(decoder0.decode(reader, m.getComment()));
        reader = rootReader.newObjectReader("dates");
        {
            java.util.ArrayList<com.meeetlet.common.event.Response> elements = new java.util.ArrayList<com.meeetlet.common.event.Response>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("dates");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.meeetlet.common.event.Response)null, com.meeetlet.common.event.Response.class));
                }
                m.setDates(elements);
            }
        }
        reader = rootReader.newObjectReader("genres");
        {
            java.util.ArrayList<com.meeetlet.common.event.Response> elements = new java.util.ArrayList<com.meeetlet.common.event.Response>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("genres");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.meeetlet.common.event.Response)null, com.meeetlet.common.event.Response.class));
                }
                m.setGenres(elements);
            }
        }
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("places");
        {
            java.util.ArrayList<com.meeetlet.common.event.Response> elements = new java.util.ArrayList<com.meeetlet.common.event.Response>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("places");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.meeetlet.common.event.Response)null, com.meeetlet.common.event.Response.class));
                }
                m.setPlaces(elements);
            }
        }
        reader = rootReader.newObjectReader("shops");
        {
            java.util.ArrayList<com.meeetlet.common.event.Response> elements = new java.util.ArrayList<com.meeetlet.common.event.Response>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("shops");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.meeetlet.common.event.Response)null, com.meeetlet.common.event.Response.class));
                }
                m.setShops(elements);
            }
        }
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}