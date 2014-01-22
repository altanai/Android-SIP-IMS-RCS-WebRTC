/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/richcall/IGeolocSharingEventListener.aidl
 */
package com.orangelabs.rcs.service.api.client.richcall;
/**
 * Geoloc sharing event listener
 */
public interface IGeolocSharingEventListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener))) {
return ((com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener)iin);
}
return new com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener.Stub.Proxy(obj);
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
case TRANSACTION_handleSharingError:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.handleSharingError(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleGeolocTransfered:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.GeolocPush _arg0;
if ((0!=data.readInt())) {
_arg0 = com.orangelabs.rcs.service.api.client.messaging.GeolocPush.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.handleGeolocTransfered(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.richcall.IGeolocSharingEventListener
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
// Geoloc has been transfered

@Override public void handleGeolocTransfered(com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((geoloc!=null)) {
_data.writeInt(1);
geoloc.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_handleGeolocTransfered, _data, _reply, 0);
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
static final int TRANSACTION_handleSharingError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_handleGeolocTransfered = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
// Session is started

public void handleSessionStarted() throws android.os.RemoteException;
// Session has been aborted

public void handleSessionAborted(int reason) throws android.os.RemoteException;
// Session has been terminated by remote

public void handleSessionTerminatedByRemote() throws android.os.RemoteException;
// Content sharing error

public void handleSharingError(int error) throws android.os.RemoteException;
// Geoloc has been transfered

public void handleGeolocTransfered(com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException;
}
