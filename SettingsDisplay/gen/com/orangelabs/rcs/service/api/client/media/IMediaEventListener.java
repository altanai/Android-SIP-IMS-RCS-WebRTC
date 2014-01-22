/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/media/IMediaEventListener.aidl
 */
package com.orangelabs.rcs.service.api.client.media;
/**
 * Media event listener
 */
public interface IMediaEventListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.media.IMediaEventListener
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.media.IMediaEventListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.media.IMediaEventListener interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.media.IMediaEventListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.media.IMediaEventListener))) {
return ((com.orangelabs.rcs.service.api.client.media.IMediaEventListener)iin);
}
return new com.orangelabs.rcs.service.api.client.media.IMediaEventListener.Stub.Proxy(obj);
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
case TRANSACTION_mediaOpened:
{
data.enforceInterface(DESCRIPTOR);
this.mediaOpened();
reply.writeNoException();
return true;
}
case TRANSACTION_mediaResized:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.mediaResized(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_mediaClosed:
{
data.enforceInterface(DESCRIPTOR);
this.mediaClosed();
reply.writeNoException();
return true;
}
case TRANSACTION_mediaStarted:
{
data.enforceInterface(DESCRIPTOR);
this.mediaStarted();
reply.writeNoException();
return true;
}
case TRANSACTION_mediaStopped:
{
data.enforceInterface(DESCRIPTOR);
this.mediaStopped();
reply.writeNoException();
return true;
}
case TRANSACTION_mediaError:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.mediaError(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.media.IMediaEventListener
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
// Media is opened

@Override public void mediaOpened() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_mediaOpened, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// The size of media has changed

@Override public void mediaResized(int width, int height) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(width);
_data.writeInt(height);
mRemote.transact(Stub.TRANSACTION_mediaResized, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Media is closed

@Override public void mediaClosed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_mediaClosed, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Media is started

@Override public void mediaStarted() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_mediaStarted, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Media is stopped

@Override public void mediaStopped() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_mediaStopped, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Media has failed

@Override public void mediaError(java.lang.String error) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(error);
mRemote.transact(Stub.TRANSACTION_mediaError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_mediaOpened = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_mediaResized = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_mediaClosed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_mediaStarted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_mediaStopped = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_mediaError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
// Media is opened

public void mediaOpened() throws android.os.RemoteException;
// The size of media has changed

public void mediaResized(int width, int height) throws android.os.RemoteException;
// Media is closed

public void mediaClosed() throws android.os.RemoteException;
// Media is started

public void mediaStarted() throws android.os.RemoteException;
// Media is stopped

public void mediaStopped() throws android.os.RemoteException;
// Media has failed

public void mediaError(java.lang.String error) throws android.os.RemoteException;
}
