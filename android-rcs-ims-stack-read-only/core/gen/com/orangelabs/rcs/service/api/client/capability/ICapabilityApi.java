/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/android /android-rcs-ims-stack-read-only/core/src/com/orangelabs/rcs/service/api/client/capability/ICapabilityApi.aidl
 */
package com.orangelabs.rcs.service.api.client.capability;
/**
 * Capability API
 */
public interface ICapabilityApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.orangelabs.rcs.service.api.client.capability.ICapabilityApi
{
private static final java.lang.String DESCRIPTOR = "com.orangelabs.rcs.service.api.client.capability.ICapabilityApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.orangelabs.rcs.service.api.client.capability.ICapabilityApi interface,
 * generating a proxy if needed.
 */
public static com.orangelabs.rcs.service.api.client.capability.ICapabilityApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.orangelabs.rcs.service.api.client.capability.ICapabilityApi))) {
return ((com.orangelabs.rcs.service.api.client.capability.ICapabilityApi)iin);
}
return new com.orangelabs.rcs.service.api.client.capability.ICapabilityApi.Stub.Proxy(obj);
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
case TRANSACTION_requestCapabilities:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.orangelabs.rcs.service.api.client.capability.Capabilities _result = this.requestCapabilities(_arg0);
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
case TRANSACTION_refreshAllCapabilities:
{
data.enforceInterface(DESCRIPTOR);
this.refreshAllCapabilities();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.orangelabs.rcs.service.api.client.capability.ICapabilityApi
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
// Request capabilities for a contact

@Override public com.orangelabs.rcs.service.api.client.capability.Capabilities requestCapabilities(java.lang.String contact) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.orangelabs.rcs.service.api.client.capability.Capabilities _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contact);
mRemote.transact(Stub.TRANSACTION_requestCapabilities, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.orangelabs.rcs.service.api.client.capability.Capabilities.CREATOR.createFromParcel(_reply);
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
// Refresh capabilities for all contacts

@Override public void refreshAllCapabilities() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_refreshAllCapabilities, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_requestCapabilities = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_refreshAllCapabilities = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
// Request capabilities for a contact

public com.orangelabs.rcs.service.api.client.capability.Capabilities requestCapabilities(java.lang.String contact) throws android.os.RemoteException;
// Refresh capabilities for all contacts

public void refreshAllCapabilities() throws android.os.RemoteException;
}
