/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/terms/ITermsApi.aidl
 */
package com.orangelabs.rcs.service.api.client.terms;
/**
 * Terms & conditions API
 */
public interface ITermsApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.terms.ITermsApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.terms.ITermsApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.terms.ITermsApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.terms.ITermsApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.terms.ITermsApi))) {
return ((com.orangelabs.rcs.service.api.client.terms.ITermsApi)iin);
}
return new com.orangelabs.rcs.service.api.client.terms.ITermsApi.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_acceptTerms:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.acceptTerms(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_rejectTerms:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.rejectTerms(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.terms.ITermsApi
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
// Accept terms and conditions via SIP

@Override public boolean acceptTerms(java.lang.String id, java.lang.String pin) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
_data.writeString(pin);
mRemote.transact(Stub.TRANSACTION_acceptTerms, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Reject terms and conditions via SIP

@Override public boolean rejectTerms(java.lang.String id, java.lang.String pin) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
_data.writeString(pin);
mRemote.transact(Stub.TRANSACTION_rejectTerms, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_acceptTerms = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_rejectTerms = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
// Accept terms and conditions via SIP

public boolean acceptTerms(java.lang.String id, java.lang.String pin) throws android.os.RemoteException;
// Reject terms and conditions via SIP

public boolean rejectTerms(java.lang.String id, java.lang.String pin) throws android.os.RemoteException;
}
