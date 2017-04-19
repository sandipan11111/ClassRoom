<?php
require_once 'DbOperation.php';

$db = new DbOperation();

$response = array();
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $messages=$db->getMessages();
  $response['error']=false;
  $response['messages']=array();

  while($row = mysqli_fetch_array($messages)){
      $temp = array();
      $temp['id']=$row['id'];
      $temp['message']=$row['message'];
      $temp['userid']=$row['users_id'];
      $temp['sentat']=$row['sentat'];
      $temp['name']=$row['name'];
      array_push($response['messages'],$temp);
  }
}else{
  $response['error']=true;
}
echo json_encode($response);
