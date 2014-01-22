/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/richcall/IImageSharingEventListener.aidl
 */
package com.orangelabs.rcs.service.api.client.richcall;
/**
 * Image sharing event listener
 */
public interface IImageSharingEventListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener))) {
return ((com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener)iin);
}
return new com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener.Stub.Proxy(obj);
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
case TRANSACTION_handleSessionStarted:
{
data.enforceInterface(DESCRIPTOR);
this.handleSessionStarted();
reply.writeNoException();
return true;
}
case TRANSACTION_handleSessionAborted:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.handleSessionAborted(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleSessionTerminatedByRemote:
{
data.enforceInterface(DESCRIPTOR);
this.handleSessionTerminatedByRemote();
reply.writeNoException();
return true;
}
case TRANSACTION_handleSharingProgress:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
long _arg1;
_arg1 = data.readLong();
this.handleSharingProgress(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_handleSharingError:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.handleSharingError(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleImageTransfered:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.handleImageTransfered(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.richcall.IImageSharingEventListener
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
// Session is started

@Override public void handleSessionStarted() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_handleSessionStarted, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Session has been aborted

@Override public void handleSessionAborted(int reason) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(reason);
mRemote.transact(Stub.TRANSACTION_handleSessionAborted, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Session has been terminated by remote

@Override public void handleSessionTerminatedByRemote() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_handleSessionTerminatedByRemote, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Content sharing progress

@Override public void handleSharingProgress(long currentSize, long totalSize) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(currentSize);
_data.writeLong(totalSize);
mRemote.transact(Stub.TRANSACTION_handleSharingProgress, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Content sharing error

@Override public void handleSharingError(int error) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(error);
mRemote.transact(Stub.TRANSACTION_handleSharingError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Image has been transfered

@Override public void handleImageTransfered(java.lang.String filename) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(filename);
mRemote.transact(Stub.TRANSACTION_handleImageTransfered, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_handleSessionStarted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_handleSessionAborted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_handleSessionTerminatedByRemote = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_handleSharingProgress = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_handleSharingError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_handleImageTransfered = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
// Session is started

public void handleSessionStarted() throws android.os.RemoteException;
// Session has been aborted

public void handleSessionAborted(int reason) throws android.os.RemoteException;
// Session has been terminated by remote

public void handleSessionTerminatedByRemote() throws android.os.RemoteException;
// Content sharing progress

public void handleSharingProgress(long currentSize, long totalSize) throws android.os.RemoteException;
// Content sharing error

public void handleSharingError(int error) throws android.os.RemoteException;
// Image has been transfered

public void handleImageTransfered(java.lang.String filename) throws android.os.RemoteException;
}
