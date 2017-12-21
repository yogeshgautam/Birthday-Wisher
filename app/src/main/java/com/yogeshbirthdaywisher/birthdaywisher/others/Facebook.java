//package com.yogeshbirthdaywisher.birthdaywisher.others;
//
//import android.app.DownloadManager;
//import android.content.pm.PackageInstaller;
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by yogesh on 7/29/2017.
// */
//public class Facebook {
//
//
//    private void requestFacebookFriends(PackageInstaller.Session session) {
//        Request.executeMyFriendsRequestAsync(session,
//                new DownloadManager.Request.GraphUserListCallback() {
//                    @Override
//                    public void onCompleted(List<GraphUser> users,
//                                            Response response) {
//                        // TODO: your code for friends here!
//                    }
//                });
//    }
//
//    private DownloadManager.Request createRequest(PackageInstaller.Session session) {
//        DownloadManager.Request request = DownloadManager.Request.newGraphPathRequest(session, "me/friends", null);
//
//        Set<String> fields = new HashSet<String>();
//        String[] requiredFields = new String[] { "id", "name", "picture",
//                "installed" };
//        fields.addAll(Arrays.asList(requiredFields));
//
//        Bundle parameters = request.getParameters();
//        parameters.putString("fields", TextUtils.join(",", fields));
//        request.setParameters(parameters);
//
//        return request;
//    }
//
//    private void requestMyAppFacebookFriends(Session session) {
//        DownloadManager.Request friendsRequest = createRequest(session);
//        friendsRequest.setCallback(new Request.Callback() {
//
//            @Override
//            public void onCompleted(Response response) {
//                List<GraphUser> friends = getResults(response);
//                // TODO: your code here
//            }
//        });
//        friendsRequest.executeAsync();
//    }
//
//    private List<GraphUser> getResults(Response response) {
//        GraphMultiResult multiResult = response
//                .getGraphObjectAs(GraphMultiResult.class);
//        GraphObjectList<GraphObject> data = multiResult.getData();
//        return data.castToListOf(GraphUser.class);
//    }
//
//    GraphUser user = friends.get(0);
//    boolean installed = false;
//    if(user.getProperty("installed") != null)
//    installed = (Boolean) user.getProperty("installed");
//}
