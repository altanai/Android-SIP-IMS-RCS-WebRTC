/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/messaging/IChatSession.aidl
 */
package com.orangelabs.rcs.service.api.client.messaging;
/**
 * Chat session interface
 */
public interface IChatSession extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.messaging.IChatSession
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.messaging.IChatSession";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.messaging.IChatSession interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.messaging.IChatSession asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.messaging.IChatSession))) {
return ((com.orangelabs.rcs.service.api.client.messaging.IChatSession)iin);
}
return new com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.Proxy(obj);
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
case TRANSACTION_getChatID:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getChatID();
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
case TRANSACTION_isGroupChat:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isGroupChat();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isStoreAndForward:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isStoreAndForward();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getFirstMessage:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.InstantMessage _result = this.getFirstMessage();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getSubject:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getSubject();
reply.writeNoException();
reply.writeString(_result);
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
case TRANSACTION_getParticipants:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _result = this.getParticipants();
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getMaxParticipants:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getMaxParticipants();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getMaxParticipantsToBeAdded:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getMaxParticipantsToBeAdded();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_addParticipant:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.addParticipant(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_addParticipants:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = data.createStringArrayList();
this.addParticipants(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_sendMessage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.sendMessage(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_isGeolocSupported:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isGeolocSupported();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_sendGeoloc:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.GeolocPush _arg0;
if ((0!=data.readInt())) {
_arg0 = com.orangelabs.rcs.service.api.client.messaging.GeolocPush.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _result = this.sendGeoloc(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_isFileTransferSupported:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isFileTransferSupported();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_sendFile:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
this.sendFile(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setIsComposingStatus:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setIsComposingStatus(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setMessageDeliveryStatus:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.setMessageDeliveryStatus(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_addSessionListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.IChatEventListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.messaging.IChatEventListener.Stub.asInterface(data.readStrongBinder());
this.addSessionListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_removeSessionListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.IChatEventListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.messaging.IChatEventListener.Stub.asInterface(data.readStrongBinder());
this.removeSessionListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.messaging.IChatSession
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
// Get chat ID

@Override public java.lang.String getChatID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getChatID, _data, _reply, 0);
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
// Is group chat

@Override public boolean isGroupChat() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isGroupChat, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Is Store & Forward

@Override public boolean isStoreAndForward() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isStoreAndForward, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get first message exchanged during the session

@Override public com.orangelabs.rcs.service.api.client.messaging.InstantMessage getFirstMessage() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.InstantMessage _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getFirstMessage, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.orangelabs.rcs.service.api.client.messaging.InstantMessage.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get subject associated to the session

@Override public java.lang.String getSubject() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSubject, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
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
// Get list of participants in the session

@Override public java.util.List<java.lang.String> getParticipants() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getParticipants, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get max number of participants in the session

@Override public int getMaxParticipants() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMaxParticipants, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get max number of participants which can be added to the conference

@Override public int getMaxParticipantsToBeAdded() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMaxParticipantsToBeAdded, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Add a participant to the session

@Override public void addParticipant(java.lang.String participant) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(participant);
mRemote.transact(Stub.TRANSACTION_addParticipant, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Add a list of participants to the session

@Override public void addParticipants(java.util.List<java.lang.String> participants) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(participants);
mRemote.transact(Stub.TRANSACTION_addParticipants, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Send a text message

@Override public java.lang.String sendMessage(java.lang.String text) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(text);
mRemote.transact(Stub.TRANSACTION_sendMessage, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Is geoloc supported

@Override public boolean isGeolocSupported() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isGeolocSupported, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Send a geoloc message

@Override public java.lang.String sendGeoloc(com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((geoloc!=null)) {
_data.writeInt(1);
geoloc.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_sendGeoloc, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Is file transfer supported

@Override public boolean isFileTransferSupported() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isFileTransferSupported, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Send file to participants

@Override public void sendFile(java.lang.String filename, boolean thumbnail) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(filename);
_data.writeInt(((thumbnail)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_sendFile, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Set is composing status

@Override public void setIsComposingStatus(boolean status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((status)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setIsComposingStatus, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Set message delivery status

@Override public void setMessageDeliveryStatus(java.lang.String msgId, java.lang.String status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(msgId);
_data.writeString(status);
mRemote.transact(Stub.TRANSACTION_setMessageDeliveryStatus, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Add session listener

@Override public void addSessionListener(com.orangelabs.rcs.service.api.client.messaging.IChatEventListener listener) throws android.os.RemoteException
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

@Override public void removeSessionListener(com.orangelabs.rcs.service.api.client.messaging.IChatEventListener listener) throws android.os.RemoteException
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
static final int TRANSACTION_getChatID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getRemoteContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getSessionState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_isGroupChat = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_isStoreAndForward = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getFirstMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getSubject = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_acceptSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_rejectSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_cancelSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getParticipants = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getMaxParticipants = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getMaxParticipantsToBeAdded = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_addParticipant = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_addParticipants = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_sendMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_isGeolocSupported = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_sendGeoloc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_isFileTransferSupported = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_sendFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_setIsComposingStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_setMessageDeliveryStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_addSessionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_removeSessionListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
}
// Get session ID

public java.lang.String getSessionID() throws android.os.RemoteException;
// Get chat ID

public java.lang.String getChatID() throws android.os.RemoteException;
// Get remote contact

public java.lang.String getRemoteContact() throws android.os.RemoteException;
// Get session state

public int getSessionState() throws android.os.RemoteException;
// Is group chat

public boolean isGroupChat() throws android.os.RemoteException;
// Is Store & Forward

public boolean isStoreAndForward() throws android.os.RemoteException;
// Get first message exchanged during the session

public com.orangelabs.rcs.service.api.client.messaging.InstantMessage getFirstMessage() throws android.os.RemoteException;
// Get subject associated to the session

public java.lang.String getSubject() throws android.os.RemoteException;
// Accept the session invitation

public void acceptSession() throws android.os.RemoteException;
// Reject the session invitation

public void rejectSession() throws android.os.RemoteException;
// Cancel the session

public void cancelSession() throws android.os.RemoteException;
// Get list of participants in the session

public java.util.List<java.lang.String> getParticipants() throws android.os.RemoteException;
// Get max number of participants in the session

public int getMaxParticipants() throws android.os.RemoteException;
// Get max number of participants which can be added to the conference

public int getMaxParticipantsToBeAdded() throws android.os.RemoteException;
// Add a participant to the session

public void addParticipant(java.lang.String participant) throws android.os.RemoteException;
// Add a list of participants to the session

public void addParticipants(java.util.List<java.lang.String> participants) throws android.os.RemoteException;
// Send a text message

public java.lang.String sendMessage(java.lang.String text) throws android.os.RemoteException;
// Is geoloc supported

public boolean isGeolocSupported() throws android.os.RemoteException;
// Send a geoloc message

public java.lang.String sendGeoloc(com.orangelabs.rcs.service.api.client.messaging.GeolocPush geoloc) throws android.os.RemoteException;
// Is file transfer supported

public boolean isFileTransferSupported() throws android.os.RemoteException;
// Send file to participants

public void sendFile(java.lang.String filename, boolean thumbnail) throws android.os.RemoteException;
// Set is composing status

public void setIsComposingStatus(boolean status) throws android.os.RemoteException;
// Set message delivery status

public void setMessageDeliveryStatus(java.lang.String msgId, java.lang.String status) throws android.os.RemoteException;
// Add session listener

public void addSessionListener(com.orangelabs.rcs.service.api.client.messaging.IChatEventListener listener) throws android.os.RemoteException;
// Remove session listener

public void removeSessionListener(com.orangelabs.rcs.service.api.client.messaging.IChatEventListener listener) throws android.os.RemoteException;
}
