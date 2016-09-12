package it.asfitness.FitUnionAndroidAdmin.data;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Clienti
{
  private Integer ingressi_totali;
  private String cognome;
  private java.util.Date created;
  private String nome;
  private String objectId;
  private String userId;
  private Integer ingressi_disponibili;
  private java.util.Date updated;
  private String email;
  private String ownerId;
  public Integer getIngressi_totali()
  {
    return ingressi_totali;
  }

  public void setIngressi_totali( Integer ingressi_totali )
  {
    this.ingressi_totali = ingressi_totali;
  }

  public String getCognome()
  {
    return cognome;
  }

  public void setCognome( String cognome )
  {
    this.cognome = cognome;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getNome()
  {
    return nome;
  }

  public void setNome( String nome )
  {
    this.nome = nome;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId( String userId )
  {
    this.userId = userId;
  }

  public Integer getIngressi_disponibili()
  {
    return ingressi_disponibili;
  }

  public void setIngressi_disponibili( Integer ingressi_disponibili )
  {
    this.ingressi_disponibili = ingressi_disponibili;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( String email )
  {
    this.email = email;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public Clienti save()
  {
    return Backendless.Data.of( Clienti.class ).save( this );
  }

  public Future<Clienti> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Clienti> future = new Future<Clienti>();
      Backendless.Data.of( Clienti.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Clienti> callback )
  {
    Backendless.Data.of( Clienti.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Clienti.class ).remove( this );
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
      Backendless.Data.of( Clienti.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Clienti.class ).remove( this, callback );
  }

  public static Clienti findById( String id )
  {
    return Backendless.Data.of( Clienti.class ).findById( id );
  }

  public static Future<Clienti> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Clienti> future = new Future<Clienti>();
      Backendless.Data.of( Clienti.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Clienti> callback )
  {
    Backendless.Data.of( Clienti.class ).findById( id, callback );
  }

  public static Clienti findFirst()
  {
    return Backendless.Data.of( Clienti.class ).findFirst();
  }

  public static Future<Clienti> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Clienti> future = new Future<Clienti>();
      Backendless.Data.of( Clienti.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Clienti> callback )
  {
    Backendless.Data.of( Clienti.class ).findFirst( callback );
  }

  public static Clienti findLast()
  {
    return Backendless.Data.of( Clienti.class ).findLast();
  }

  public static Future<Clienti> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Clienti> future = new Future<Clienti>();
      Backendless.Data.of( Clienti.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Clienti> callback )
  {
    Backendless.Data.of( Clienti.class ).findLast( callback );
  }

  public static BackendlessCollection<Clienti> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Clienti.class ).find( query );
  }

  public static Future<BackendlessCollection<Clienti>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Clienti>> future = new Future<BackendlessCollection<Clienti>>();
      Backendless.Data.of( Clienti.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Clienti>> callback )
  {
    Backendless.Data.of( Clienti.class ).find( query, callback );
  }
}