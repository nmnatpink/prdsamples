package com.nmn.mylibrary;

import android.content.Context;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by nmn on 23/8/17.
 */

public  class PRDclass {

// Please make sure since this is a network call so it needs to be done off the main thread for smooth working of app

    public void uploadViaSFTP(final String sourcePath, final String destinationPath,
                              final String IPAddress, final String userName,
                              final String password, final int portNumber)
    {


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {


                //      uploadFile();

                try {
                    JSch ssh = new JSch();

                    Session session = ssh.getSession(userName, IPAddress, portNumber);
                    // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
                    // Man In the Middle attacks
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.setPassword(password);

                    session.connect();
                    Channel channel = session.openChannel("sftp");
                    channel.connect();

                    ChannelSftp sftp = (ChannelSftp) channel;


                    if (sftp.isConnected())
                    {
                        Log.v("check","working");

                        // to check connection with server
                        sftp.put(sourcePath, destinationPath);
                    }
                    //  sftp.cd(directory);


                    // use the put method , if you are using android remember to remove "file://" and use only the relative path







                    channel.disconnect();
                    session.disconnect();
                } catch (JSchException e) {
                    System.out.println(e.getMessage().toString());
                    e.printStackTrace();
                } catch (SftpException e) {
                    System.out.println(e.getMessage().toString());
                    e.printStackTrace();
                }


            }
        });

        thread.start();

    }



    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
        File file = new File(mcoContext.getFilesDir(),"mydir");
        if(!file.exists()){
            file.mkdir();
        }







        try{
            File gpxfile = new File(file, sFileName);
            Log.v("checkPath",gpxfile.getAbsolutePath());
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();




        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}

