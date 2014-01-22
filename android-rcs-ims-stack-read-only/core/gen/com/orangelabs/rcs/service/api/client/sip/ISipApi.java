/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/sip/ISipApi.aidl
 */
package com.orangelabs.rcs.service.api.client.sip;
/**
 * SIP API
 */
public interface ISipApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.sip.ISipApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.sip.ISipApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.sip.ISipApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.sip.ISipApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.sip.ISipApi))) {
return ((com.orangelabs.rcs.service.api.client.sip.ISipApi)iin);
}
return new com.orangelabs.rcs.service.api.client.sip.ISipApi.Stub.Proxy(obj);
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
case TRANSACTION_initiateSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
com.orangelabs.rcs.service.api.client.sip.ISipSession _result = this.initiateSession(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.sip.ISipSession _result = this.getSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_getSessions:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<android.os.IBinder> _result = this.getSessions();
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_sendSipInstantMessage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
boolean _result = this.sendSipInstantMessage(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.sip.ISipApi
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
// Initiate a SIP session

@Override public com.orangelabs.rcs.service.api.client.sip.ISipSession initiateSession(java.lang.String contact, java.lang.String featureTag, java.lang.String sdp) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.sip.ISipSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(featureTag);
_data.writeString(sdp);
mRemote.transact(Stub.TRANSACTION_initiateSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.sip.ISipSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current SIP session from its session ID

@Override public com.orangelabs.rcs.service.api.client.sip.ISipSession getSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.sip.ISipSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.sip.ISipSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current SIP sessions with a contact

@Override public java.util.List<android.os.IBinder> getSessionsWith(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_getSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current SIP sessions

@Override public java.util.List<android.os.IBinder> getSessions() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSessions, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Send an instant message (SIP MESSAGE)

@Override public boolean sendSipInstantMessage(java.lang.String contact, java.lang.String featureTag, java.lang.String content, java.lang.String contentType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(featureTag);
_data.writeString(content);
_data.writeString(contentType);
mRemote.transact(Stub.TRANSACTION_sendSipInstantMessage, _data, _reply, 0);
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
static final int TRANSACTION_initiateSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_sendSipInstantMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
// Initiate a SIP session

public com.orangelabs.rcs.service.api.client.sip.ISipSession initiateSession(java.lang.String contact, java.lang.String featureTag, java.lang.String sdp) throws android.os.RemoteException;
// Get current SIP session from its session ID

public com.orangelabs.rcs.service.api.client.sip.ISipSession getSession(java.lang.String id) throws android.os.RemoteException;
// Get list of current SIP sessions with a contact

public java.util.List<android.os.IBinder> getSessionsWith(java.lang.String contact) throws android.os.RemoteException;
// Get list of current SIP sessions

public java.util.List<android.os.IBinder> getSessions() throws android.os.RemoteException;
// Send an instant message (SIP MESSAGE)

public boolean sendSipInstantMessage(java.lang.String contact, java.lang.String featureTag, java.lang.String content, java.lang.String contentType) throws android.os.RemoteException;
}
