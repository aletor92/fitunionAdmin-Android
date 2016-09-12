package it.asfitness.FitUnionAndroidAdmin.data;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Palestre
{
  private String idAdmin;
  private Integer level;
  private Double latitude;
  private Double longitude;
  private Boolean attivo;
  private String objectId;
  private String via;
  private Double telefono;
  private String nome;
  private String email;
  private java.util.Date updated;
  private String ownerId;
  private java.util.Date created;
  private java.util.Date dataIscrizione;
  public String getIdAdmin()
  {
    return idAdmin;
  }

  public void setIdAdmin( String idAdmin )
  {
    this.idAdmin = idAdmin;
  }

  public Integer getLevel()
  {
    return level;
  }

  public void setLevel( Integer level )
  {
    this.level = level;
  }

  public Double getLatitude()
  {
    return latitude;
  }

  public void setLatitude( Double latitude )
  {
    this.latitude = latitude;
  }

  public Double getLongitude()
  {
    return longitude;
  }

  public void setLongitude( Double longitude )
  {
    this.longitude = longitude;
  }

  public Boolean getAttivo()
  {
    return attivo;
  }

  public void setAttivo( Boolean attivo )
  {
    this.attivo = attivo;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getVia()
  {
    return via;
  }

  public void setVia( String via )
  {
    this.via = via;
  }

  public Double getTelefono()
  {
    return telefono;
  }

  public void setTelefono( Double telefono )
  {
    this.telefono = telefono;
  }

  public String getNome()
  {
    return nome;
  }

  public void setNome( String nome )
  {
    this.nome = nome;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public java.util.Date getDataIscrizione()
  {
    return dataIscrizione;
  }

  public void setDataIscrizione( java.util.Date dataIscrizione )
  {
    this.dataIscrizione = dataIscrizione;
  }

                                                    
  public Palestre save()
  {
    return Backendless.Data.of( Palestre.class ).save( this );
  }

  public Future<Palestre> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Palestre> future = new Future<Palestre>();
      Backendless.Data.of( Palestre.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Palestre> callback )
  {
    Backendless.Data.of( Palestre.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Palestre.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Palestre.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Palestre.class ).remove( this, callback );
  }

  public static Palestre findById( String id )
  {
    return Backendless.Data.of( Palestre.class ).findById( id );
  }

  public static Future<Palestre> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Palestre> future = new Future<Palestre>();
      Backendless.Data.of( Palestre.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Palestre> callback )
  {
    Backendless.Data.of( Palestre.class ).findById( id, callback );
  }

  public static Palestre findFirst()
  {
    return Backendless.Data.of( Palestre.class ).findFirst();
  }

  public static Future<Palestre> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Palestre> future = new Future<Palestre>();
      Backendless.Data.of( Palestre.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Palestre> callback )
  {
    Backendless.Data.of( Palestre.class ).findFirst( callback );
  }

  public static Palestre findLast()
  {
    return Backendless.Data.of( Palestre.class ).findLast();
  }

  public static Future<Palestre> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Palestre> future = new Future<Palestre>();
      Backendless.Data.of( Palestre.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Palestre> callback )
  {
    Backendless.Data.of( Palestre.class ).findLast( callback );
  }

  public static BackendlessCollection<Palestre> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Palestre.class ).find( query );
  }

  public static Future<BackendlessCollection<Palestre>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Palestre>> future = new Future<BackendlessCollection<Palestre>>();
      Backendless.Data.of( Palestre.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Palestre>> callback )
  {
    Backendless.Data.of( Palestre.class ).find( query, callback );
  }
}