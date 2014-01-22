/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/richcall/IRichCallApi.aidl
 */
package com.orangelabs.rcs.service.api.client.richcall;
/**
 * Rich call API
 */
public interface IRichCallApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.richcall.IRichCallApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.richcall.IRichCallApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.richcall.IRichCallApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.richcall.IRichCallApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.richcall.IRichCallApi))) {
return ((com.orangelabs.rcs.service.api.client.richcall.IRichCallApi)iin);
}
return new com.orangelabs.rcs.service.api.client.richcall.IRichCallApi.Stub.Proxy(obj);
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
case TRANSACTION_getRemotePhoneNumber:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getRemotePhoneNumber();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_initiateLiveVideoSharing:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.media.IMediaPlayer _arg1;
_arg1 = com.orangelabs.rcs.service.api.client.media.IMediaPlayer.Stub.asInterface(data.readStrongBinder());
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession _result = this.initiateLiveVideoSharing(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getVideoSharingSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession _result = this.getVideoSharingSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getVideoSharingSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getVideoSharingSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_initiateImageSharing:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession _result = this.initiateImageSharing(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getImageSharingSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession _result = this.getImageSharingSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getImageSharingSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getImageSharingSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_setMultiPartyCall:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setMultiPartyCall(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setCallHold:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setCallHold(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_initiateGeolocSharing:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.GeolocPush _arg1;
if ((0!=data.readInt())) {
_arg1 = com.orangelabs.rcs.service.api.client.messaging.GeolocPush.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession _result = this.initiateGeolocSharing(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getGeolocSharingSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession _result = this.getGeolocSharingSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.richcall.IRichCallApi
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
// Get the remote phone number involved in the current call

@Override public java.lang.String getRemotePhoneNumber() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRemotePhoneNumber, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Initiate a live video sharing session

@Override public com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession initiateLiveVideoSharing(java.lang.String contact, com.orangelabs.rcs.service.api.client.media.IMediaPlayer player) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeStrongBinder((((player!=null))?(player.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_initiateLiveVideoSharing, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current video sharing session from its session ID

@Override public com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession getVideoSharingSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getVideoSharingSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current video sharing sessions with a contact

@Override public java.util.List<android.os.IBinder> getVideoSharingSessionsWith(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_getVideoSharingSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Initiate an image sharing session

@Override public com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession initiateImageSharing(java.lang.String contact, java.lang.String file, boolean thumbnail) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(file);
_data.writeInt(((thumbnail)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_initiateImageSharing, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current image sharing session from its session ID

@Override public com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession getImageSharingSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getImageSharingSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current image sharing sessions with a contact

@Override public java.util.List<android.os.IBinder> getImageSharingSessionsWith(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_getImageSharingSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Set multiparty call

@Override public void setMultiPartyCall(boolean flag) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((flag)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setMultiPartyCall, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Set call hold

@Override public void setCallHold(boolean flag) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((flag)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCallHold, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Initiate a geoloc sharing session

@Override public com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession initiateGeolocSharing(java.lang.String contact, com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
if ((geoloc!=null)) {
_data.writeInt(1);
geoloc.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_initiateGeolocSharing, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current geoloc sharing session from its session ID

@Override public com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession getGeolocSharingSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getGeolocSharingSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getRemotePhoneNumber = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_initiateLiveVideoSharing = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getVideoSharingSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getVideoSharingSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_initiateImageSharing = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getImageSharingSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getImageSharingSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_setMultiPartyCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_setCallHold = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_initiateGeolocSharing = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getGeolocSharingSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
// Get the remote phone number involved in the current call

public java.lang.String getRemotePhoneNumber() throws android.os.RemoteException;
// Initiate a live video sharing session

public com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession initiateLiveVideoSharing(java.lang.String contact, com.orangelabs.rcs.service.api.client.media.IMediaPlayer player) throws android.os.RemoteException;
// Get current video sharing session from its session ID

public com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession getVideoSharingSession(java.lang.String id) throws android.os.RemoteException;
// Get list of current video sharing sessions with a contact

public java.util.List<android.os.IBinder> getVideoSharingSessionsWith(java.lang.String contact) throws android.os.RemoteException;
// Initiate an image sharing session

public com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession initiateImageSharing(java.lang.String contact, java.lang.String file, boolean thumbnail) throws android.os.RemoteException;
// Get current image sharing session from its session ID

public com.orangelabs.rcs.service.api.client.richcall.IImageSharingSession getImageSharingSession(java.lang.String id) throws android.os.RemoteException;
// Get list of current image sharing sessions with a contact

public java.util.List<android.os.IBinder> getImageSharingSessionsWith(java.lang.String contact) throws android.os.RemoteException;
// Set multiparty call

public void setMultiPartyCall(boolean flag) throws android.os.RemoteException;
// Set call hold

public void setCallHold(boolean flag) throws android.os.RemoteException;
// Initiate a geoloc sharing session

public com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession initiateGeolocSharing(java.lang.String contact, com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException;
// Get current geoloc sharing session from its session ID

public com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingSession getGeolocSharingSession(java.lang.String id) throws android.os.RemoteException;
}
