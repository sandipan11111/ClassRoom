<?php

class Push {
    //notification title
    private $name;

    //notification message
    private $message;
    private $id;



    //initializing values in this constructor
    function __construct($title, $message,$id) {
         $this->title = $title;
         $this->message = $message;
         $this->id=$id;
    }

    //getting the push notification
    public function getPush() {
        $res = array();
        $res['data']['title'] = $this->title;
        $res['data']['message'] = $this->message;
        $res['data']['id']=$this->id;
        return $res;
    }

}
