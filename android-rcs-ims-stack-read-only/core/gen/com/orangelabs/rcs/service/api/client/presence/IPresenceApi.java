/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/presence/IPresenceApi.aidl
 */
package com.orangelabs.rcs.service.api.client.presence;
/**
 * Presence API
 */
public interface IPresenceApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.presence.IPresenceApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.presence.IPresenceApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.presence.IPresenceApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.presence.IPresenceApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.presence.IPresenceApi))) {
return ((com.orangelabs.rcs.service.api.client.presence.IPresenceApi)iin);
}
return new com.orangelabs.rcs.service.api.client.presence.IPresenceApi.Stub.Proxy(obj);
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
case TRANSACTION_setMyPresenceInfo:
{
data.enforceInterface(DESCRIPTOR);
com.orangelabs.rcs.service.api.client.presence.PresenceInfo _arg0;
if ((0!=data.readInt())) {
_arg0 = com.orangelabs.rcs.service.api.client.presence.PresenceInfo.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.setMyPresenceInfo(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_inviteContact:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.inviteContact(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_acceptSharingInvitation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.acceptSharingInvitation(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_rejectSharingInvitation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.rejectSharingInvitation(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_ignoreSharingInvitation:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.ignoreSharingInvitation(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_revokeContact:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.revokeContact(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unrevokeContact:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.unrevokeContact(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unblockContact:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.unblockContact(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getGrantedContacts:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _result = this.getGrantedContacts();
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getRevokedContacts:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _result = this.getRevokedContacts();
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getBlockedContacts:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _result = this.getBlockedContacts();
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.presence.IPresenceApi
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
// Set my presence info

@Override public boolean setMyPresenceInfo(com.orangelabs.rcs.service.api.client.presence.PresenceInfo info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((info!=null)) {
_data.writeInt(1);
info.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setMyPresenceInfo, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Invite a contact to share its presence

@Override public boolean inviteContact(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_inviteContact, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Accept the sharing invitation

@Override public boolean acceptSharingInvitation(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_acceptSharingInvitation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Reject the sharing invitation

@Override public boolean rejectSharingInvitation(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_rejectSharingInvitation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Ignore the sharing invitation

@Override public void ignoreSharingInvitation(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_ignoreSharingInvitation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Revoke a shared contact

@Override public boolean revokeContact(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_revokeContact, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Unrevoke a revoked contact

@Override public boolean unrevokeContact(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_unrevokeContact, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Unblock a blocked contact

@Override public boolean unblockContact(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_unblockContact, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get the list of granted contacts

@Override public java.util.List<java.lang.String> getGrantedContacts() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getGrantedContacts, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get the list of revoked contacts

@Override public java.util.List<java.lang.String> getRevokedContacts() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRevokedContacts, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Get the list of blocked contacts

@Override public java.util.List<java.lang.String> getBlockedContacts() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getBlockedContacts, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_setMyPresenceInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_inviteContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_acceptSharingInvitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_rejectSharingInvitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_ignoreSharingInvitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_revokeContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_unrevokeContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_unblockContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getGrantedContacts = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getRevokedContacts = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getBlockedContacts = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
// Set my presence info

public boolean setMyPresenceInfo(com.orangelabs.rcs.service.api.client.presence.PresenceInfo info) throws android.os.RemoteException;
// Invite a contact to share its presence

public boolean inviteContact(java.lang.String contact) throws android.os.RemoteException;
// Accept the sharing invitation

public boolean acceptSharingInvitation(java.lang.String contact) throws android.os.RemoteException;
// Reject the sharing invitation

public boolean rejectSharingInvitation(java.lang.String contact) throws android.os.RemoteException;
// Ignore the sharing invitation

public void ignoreSharingInvitation(java.lang.String contact) throws android.os.RemoteException;
// Revoke a shared contact

public boolean revokeContact(java.lang.String contact) throws android.os.RemoteException;
// Unrevoke a revoked contact

public boolean unrevokeContact(java.lang.String contact) throws android.os.RemoteException;
// Unblock a blocked contact

public boolean unblockContact(java.lang.String contact) throws android.os.RemoteException;
// Get the list of granted contacts

public java.util.List<java.lang.String> getGrantedContacts() throws android.os.RemoteException;
// Get the list of revoked contacts

public java.util.List<java.lang.String> getRevokedContacts() throws android.os.RemoteException;
// Get the list of blocked contacts

public java.util.List<java.lang.String> getBlockedContacts() throws android.os.RemoteException;
}
