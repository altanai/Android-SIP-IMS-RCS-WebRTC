/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/messaging/IChatEventListener.aidl
 */
package com.orangelabs.rcs.service.api.client.messaging;
/**
 * Chat event listener
 */
public interface IChatEventListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.messaging.IChatEventListener
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.messaging.IChatEventListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.messaging.IChatEventListener interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.messaging.IChatEventListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.messaging.IChatEventListener))) {
return ((com.orangelabs.rcs.service.api.client.messaging.IChatEventListener)iin);
}
return new com.orangelabs.rcs.service.api.client.messaging.IChatEventListener.Stub.Proxy(obj);
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
case TRANSACTION_handleReceiveMessage:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.InstantMessage _arg0;
if ((0!=data.readInt())) {
_arg0 = com.orangelabs.rcs.service.api.client.messaging.InstantMessage.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.handleReceiveMessage(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleImError:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.handleImError(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleIsComposingEvent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
this.handleIsComposingEvent(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_handleConferenceEvent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
this.handleConferenceEvent(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_handleMessageDeliveryStatus:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.handleMessageDeliveryStatus(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_handleAddParticipantSuccessful:
{
data.enforceInterface(DESCRIPTOR);
this.handleAddParticipantSuccessful();
reply.writeNoException();
return true;
}
case TRANSACTION_handleAddParticipantFailed:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.handleAddParticipantFailed(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_handleReceiveGeoloc:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.GeolocMessage _arg0;
if ((0!=data.readInt())) {
_arg0 = com.orangelabs.rcs.service.api.client.messaging.GeolocMessage.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.handleReceiveGeoloc(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.messaging.IChatEventListener
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
// New text message received

@Override public void handleReceiveMessage(com.orangelabs.rcs.service.api.client.messaging.InstantMessage msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((msg!=null)) {
_data.writeInt(1);
msg.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_handleReceiveMessage, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Chat error

@Override public void handleImError(int error) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(error);
mRemote.transact(Stub.TRANSACTION_handleImError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Is composing event

@Override public void handleIsComposingEvent(java.lang.String contact, boolean status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeInt(((status)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_handleIsComposingEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Conference event

@Override public void handleConferenceEvent(java.lang.String contact, java.lang.String contactDisplayname, java.lang.String state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(contactDisplayname);
_data.writeString(state);
mRemote.transact(Stub.TRANSACTION_handleConferenceEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Message delivery status

@Override public void handleMessageDeliveryStatus(java.lang.String msgId, java.lang.String status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(msgId);
_data.writeString(status);
mRemote.transact(Stub.TRANSACTION_handleMessageDeliveryStatus, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Request to add participant is successful

@Override public void handleAddParticipantSuccessful() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_handleAddParticipantSuccessful, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Request to add participant has failed

@Override public void handleAddParticipantFailed(java.lang.String reason) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(reason);
mRemote.transact(Stub.TRANSACTION_handleAddParticipantFailed, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// New geoloc message received

@Override public void handleReceiveGeoloc(com.orangelabs.rcs.service.api.client.messaging.GeolocMessage geoloc) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_handleReceiveGeoloc, _data, _reply, 0);
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
static final int TRANSACTION_handleReceiveMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_handleImError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_handleIsComposingEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_handleConferenceEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_handleMessageDeliveryStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_handleAddParticipantSuccessful = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_handleAddParticipantFailed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_handleReceiveGeoloc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
// Session is started

public void handleSessionStarted() throws android.os.RemoteException;
// Session has been aborted

public void handleSessionAborted(int reason) throws android.os.RemoteException;
// Session has been terminated by remote

public void handleSessionTerminatedByRemote() throws android.os.RemoteException;
// New text message received

public void handleReceiveMessage(com.orangelabs.rcs.service.api.client.messaging.InstantMessage msg) throws android.os.RemoteException;
// Chat error

public void handleImError(int error) throws android.os.RemoteException;
// Is composing event

public void handleIsComposingEvent(java.lang.String contact, boolean status) throws android.os.RemoteException;
// Conference event

public void handleConferenceEvent(java.lang.String contact, java.lang.String contactDisplayname, java.lang.String state) throws android.os.RemoteException;
// Message delivery status

public void handleMessageDeliveryStatus(java.lang.String msgId, java.lang.String status) throws android.os.RemoteException;
// Request to add participant is successful

public void handleAddParticipantSuccessful() throws android.os.RemoteException;
// Request to add participant has failed

public void handleAddParticipantFailed(java.lang.String reason) throws android.os.RemoteException;
// New geoloc message received

public void handleReceiveGeoloc(com.orangelabs.rcs.service.api.client.messaging.GeolocMessage geoloc) throws android.os.RemoteException;
}
