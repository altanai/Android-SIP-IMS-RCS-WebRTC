/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/richcall/IVideoSharingSession.aidl
 */
package com.orangelabs.rcs.service.api.client.richcall;
/**
 * Video sharing session interface
 */
public interface IVideoSharingSession extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession))) {
return ((com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession)iin);
}
return new com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession.Stub.Proxy(obj);
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
case TRANSACTION_getSessionID:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getSessionID();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getRemoteContact:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getRemoteContact();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getSessionState:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getSessionState();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_acceptSession:
{
data.enforceInterface(DESCRIPTOR);
this.acceptSession();
reply.writeNoException();
return true;
}
case TRANSACTION_rejectSession:
{
data.enforceInterface(DESCRIPTOR);
this.rejectSession();
reply.writeNoException();
return true;
}
case TRANSACTION_cancelSession:
{
data.enforceInterface(DESCRIPTOR);
this.cancelSession();
reply.writeNoException();
return true;
}
case TRANSACTION_setMediaRenderer:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.media.IMediaRenderer _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.media.IMediaRenderer.Stub.asInterface(data.readStrongBinder());
this.setMediaRenderer(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getMediaRenderer:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.media.IMediaRenderer _result = this.getMediaRenderer();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_setMediaPlayer:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.media.IMediaPlayer _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.media.IMediaPlayer.Stub.asInterface(data.readStrongBinder());
this.setMediaPlayer(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getMediaPlayer:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.media.IMediaPlayer _result = this.getMediaPlayer();
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_addSessionListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener.Stub.asInterface(data.readStrongBinder());
this.addSessionListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_removeSessionListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener.Stub.asInterface(data.readStrongBinder());
this.removeSessionListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.richcall.IVideoSharingSession
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
// Get session ID

@Override public java.lang.String getSessionID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSessionID, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get remote contact

@Override public java.lang.String getRemoteContact() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRemoteContact, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get session state

@Override public int getSessionState() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSessionState, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Accept the session invitation

@Override public void acceptSession() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_acceptSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Reject the session invitation

@Override public void rejectSession() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_rejectSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Cancel the session

@Override public void cancelSession() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_cancelSession, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Set the media renderer

@Override public void setMediaRenderer(com.orangelabs.rcs.service.api.client.media.IMediaRenderer renderer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((renderer!=null))?(renderer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setMediaRenderer, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Get the media renderer

@Override public com.orangelabs.rcs.service.api.client.media.IMediaRenderer getMediaRenderer() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.media.IMediaRenderer _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMediaRenderer, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.media.IMediaRenderer.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Set the media player

@Override public void setMediaPlayer(com.orangelabs.rcs.service.api.client.media.IMediaPlayer player) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((player!=null))?(player.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setMediaPlayer, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Get the media player

@Override public com.orangelabs.rcs.service.api.client.media.IMediaPlayer getMediaPlayer() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.media.IMediaPlayer _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMediaPlayer, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.media.IMediaPlayer.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Add session listener

@Override public void addSessionListener(com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addSessionListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Remove session listener

@Override public void removeSessionListener(com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeSessionListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getSessionID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getRemoteContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getSessionState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_acceptSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_rejectSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_cancelSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_setMediaRenderer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getMediaRenderer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_setMediaPlayer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getMediaPlayer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_addSessionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_removeSessionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
}
// Get session ID

public java.lang.String getSessionID() throws android.os.RemoteException;
// Get remote contact

public java.lang.String getRemoteContact() throws android.os.RemoteException;
// Get session state

public int getSessionState() throws android.os.RemoteException;
// Accept the session invitation

public void acceptSession() throws android.os.RemoteException;
// Reject the session invitation

public void rejectSession() throws android.os.RemoteException;
// Cancel the session

public void cancelSession() throws android.os.RemoteException;
// Set the media renderer

public void setMediaRenderer(com.orangelabs.rcs.service.api.client.media.IMediaRenderer renderer) throws android.os.RemoteException;
// Get the media renderer

public com.orangelabs.rcs.service.api.client.media.IMediaRenderer getMediaRenderer() throws android.os.RemoteException;
// Set the media player

public void setMediaPlayer(com.orangelabs.rcs.service.api.client.media.IMediaPlayer player) throws android.os.RemoteException;
// Get the media player

public com.orangelabs.rcs.service.api.client.media.IMediaPlayer getMediaPlayer() throws android.os.RemoteException;
// Add session listener

public void addSessionListener(com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener listener) throws android.os.RemoteException;
// Remove session listener

public void removeSessionListener(com.orangelabs.rcs.service.api.client.richcall.IVideoSharingEventListener listener) throws android.os.RemoteException;
}
