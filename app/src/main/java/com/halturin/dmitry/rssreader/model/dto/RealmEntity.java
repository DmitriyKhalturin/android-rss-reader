package com.halturin.dmitry.rssreader.model.dto;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 22.02.17 2:27.
 */

public class RealmEntity extends RealmObject {

//==================================================================================================
//    Class Variables
//==================================================================================================

    @PrimaryKey
    private long id;

//==================================================================================================
//    Class Methods
//==================================================================================================

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

//==================================================================================================
//    Class Specific Methods
//==================================================================================================

    public void autoIncrementId(Realm realm){
        Number currentId = realm.where(this.getClass()).max("id");
        long autoIncrementId = (currentId == null ? 0 : currentId.longValue() + 1);
        setId(autoIncrementId);
    }

}
