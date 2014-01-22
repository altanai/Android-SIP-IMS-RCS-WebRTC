/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/messaging/IMessagingApi.aidl
 */
package com.orangelabs.rcs.service.api.client.messaging;
/**
 * Messaging API
 */
public interface IMessagingApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.messaging.IMessagingApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.messaging.IMessagingApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.messaging.IMessagingApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.messaging.IMessagingApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.messaging.IMessagingApi))) {
return ((com.orangelabs.rcs.service.api.client.messaging.IMessagingApi)iin);
}
return new com.orangelabs.rcs.service.api.client.messaging.IMessagingApi.Stub.Proxy(obj);
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
case TRANSACTION_transferFile:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession _result = this.transferFile(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getFileTransferSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession _result = this.getFileTransferSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getFileTransferSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getFileTransferSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_getFileTransferSessions:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<android.os.IBinder> _result = this.getFileTransferSessions();
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_initiateOne2OneChatSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result = this.initiateOne2OneChatSession(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_initiateAdhocGroupChatSession:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = data.createStringArrayList();
java.lang.String _arg1;
_arg1 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result = this.initiateAdhocGroupChatSession(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_rejoinGroupChatSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result = this.rejoinGroupChatSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_restartGroupChatSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result = this.restartGroupChatSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getChatSession:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result = this.getChatSession(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getChatSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getChatSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_getChatSessions:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<android.os.IBinder> _result = this.getChatSessions();
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_getGroupChatSessions:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<android.os.IBinder> _result = this.getGroupChatSessions();
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_getGroupChatSessionsWith:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<android.os.IBinder> _result = this.getGroupChatSessionsWith(_arg0);
reply.writeNoException();
reply.writeBinderList(_result);
return true;
}
case TRANSACTION_setMessageDeliveryStatus:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
this.setMessageDeliveryStatus(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_addMessageDeliveryListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener.Stub.asInterface(data.readStrongBinder());
this.addMessageDeliveryListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_removeMessageDeliveryListener:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener _arg0;
_arg0 = com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener.Stub.asInterface(data.readStrongBinder());
this.removeMessageDeliveryListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.messaging.IMessagingApi
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
// Transfer a file

@Override public com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession transferFile(java.lang.String contact, java.lang.String file, boolean thumbnail) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(file);
_data.writeInt(((thumbnail)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_transferFile, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current file transfer session from its session ID

@Override public com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession getFileTransferSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getFileTransferSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current file transfer sessions with a contact

@Override public java.util.List<android.os.IBinder> getFileTransferSessionsWith(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_getFileTransferSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current file transfer sessions

@Override public java.util.List<android.os.IBinder> getFileTransferSessions() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getFileTransferSessions, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Initiate a one-to-one chat session

@Override public com.orangelabs.rcs.service.api.client.messaging.IChatSession initiateOne2OneChatSession(java.lang.String contact, java.lang.String firstMsg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
_data.writeString(firstMsg);
mRemote.transact(Stub.TRANSACTION_initiateOne2OneChatSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Initiate an ad-hoc group chat session

@Override public com.orangelabs.rcs.service.api.client.messaging.IChatSession initiateAdhocGroupChatSession(java.util.List<java.lang.String> participants, java.lang.String subject) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(participants);
_data.writeString(subject);
mRemote.transact(Stub.TRANSACTION_initiateAdhocGroupChatSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Rejoin a group chat session

@Override public com.orangelabs.rcs.service.api.client.messaging.IChatSession rejoinGroupChatSession(java.lang.String chatId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(chatId);
mRemote.transact(Stub.TRANSACTION_rejoinGroupChatSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Restart a group chat session

@Override public com.orangelabs.rcs.service.api.client.messaging.IChatSession restartGroupChatSession(java.lang.String chatId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(chatId);
mRemote.transact(Stub.TRANSACTION_restartGroupChatSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get current chat session from its session ID

@Override public com.orangelabs.rcs.service.api.client.messaging.IChatSession getChatSession(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.messaging.IChatSession _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getChatSession, _data, _reply, 0);
_reply.readException();
_result = com.orangelabs.rcs.service.api.client.messaging.IChatSession.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current chat sessions with a contact

@Override public java.util.List<android.os.IBinder> getChatSessionsWith(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_getChatSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current chat sessions

@Override public java.util.List<android.os.IBinder> getChatSessions() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getChatSessions, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current group chat sessions

@Override public java.util.List<android.os.IBinder> getGroupChatSessions() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getGroupChatSessions, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get list of current group chat sessions for a given conversation

@Override public java.util.List<android.os.IBinder> getGroupChatSessionsWith(java.lang.String chatId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<android.os.IBinder> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(chatId);
mRemote.transact(Stub.TRANSACTION_getGroupChatSessionsWith, _data, _reply, 0);
_reply.readException();
_result = _reply.createBinderArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Set message delivery status

@Override public void setMessageDeliveryStatus(java.lang.String contact, java.lang.String msgId, java.lang.String status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
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
// Add message delivery listener

@Override public void addMessageDeliveryListener(com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addMessageDeliveryListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Remove message delivery listener

@Override public void removeMessageDeliveryListener(com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeMessageDeliveryListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_transferFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getFileTransferSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getFileTransferSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getFileTransferSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_initiateOne2OneChatSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_initiateAdhocGroupChatSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_rejoinGroupChatSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_restartGroupChatSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getChatSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getChatSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getChatSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getGroupChatSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getGroupChatSessionsWith = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_setMessageDeliveryStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_addMessageDeliveryListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_removeMessageDeliveryListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
}
// Transfer a file

public com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession transferFile(java.lang.String contact, java.lang.String file, boolean thumbnail) throws android.os.RemoteException;
// Get current file transfer session from its session ID

public com.orangelabs.rcs.service.api.client.messaging.IFileTransferSession getFileTransferSession(java.lang.String id) throws android.os.RemoteException;
// Get list of current file transfer sessions with a contact

public java.util.List<android.os.IBinder> getFileTransferSessionsWith(java.lang.String contact) throws android.os.RemoteException;
// Get list of current file transfer sessions

public java.util.List<android.os.IBinder> getFileTransferSessions() throws android.os.RemoteException;
// Initiate a one-to-one chat session

public com.orangelabs.rcs.service.api.client.messaging.IChatSession initiateOne2OneChatSession(java.lang.String contact, java.lang.String firstMsg) throws android.os.RemoteException;
// Initiate an ad-hoc group chat session

public com.orangelabs.rcs.service.api.client.messaging.IChatSession initiateAdhocGroupChatSession(java.util.List<java.lang.String> participants, java.lang.String subject) throws android.os.RemoteException;
// Rejoin a group chat session

public com.orangelabs.rcs.service.api.client.messaging.IChatSession rejoinGroupChatSession(java.lang.String chatId) throws android.os.RemoteException;
// Restart a group chat session

public com.orangelabs.rcs.service.api.client.messaging.IChatSession restartGroupChatSession(java.lang.String chatId) throws android.os.RemoteException;
// Get current chat session from its session ID

public com.orangelabs.rcs.service.api.client.messaging.IChatSession getChatSession(java.lang.String id) throws android.os.RemoteException;
// Get list of current chat sessions with a contact

public java.util.List<android.os.IBinder> getChatSessionsWith(java.lang.String contact) throws android.os.RemoteException;
// Get list of current chat sessions

public java.util.List<android.os.IBinder> getChatSessions() throws android.os.RemoteException;
// Get list of current group chat sessions

public java.util.List<android.os.IBinder> getGroupChatSessions() throws android.os.RemoteException;
// Get list of current group chat sessions for a given conversation

public java.util.List<android.os.IBinder> getGroupChatSessionsWith(java.lang.String chatId) throws android.os.RemoteException;
// Set message delivery status

public void setMessageDeliveryStatus(java.lang.String contact, java.lang.String msgId, java.lang.String status) throws android.os.RemoteException;
// Add message delivery listener

public void addMessageDeliveryListener(com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener listener) throws android.os.RemoteException;
// Remove message delivery listener

public void removeMessageDeliveryListener(com.orangelabs.rcs.service.api.client.messaging.IMessageDeliveryListener listener) throws android.os.RemoteException;
}
