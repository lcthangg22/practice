<?php

namespace App\Domains\User\Validator;

use App\Domains\User\Model\DbUser;
use Illuminate\Support\Facades\Validator;

class RegisterValidator
{
//    public function validation(array $params): \Illuminate\Validation\Validator
//    {
//        $validator =  Validator::make($params, [
//            'username' => 'required|unique:users',
//            'password' => 'required|min:6',
//            'email' => 'required|unique:users|regex:/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\.[a-zA-Z0-9._%+-]+$/i',
//            'confirm_password'=> 'required|min:6|same:password'
//        ]);
//
//        $validator->setCustomMessages([
//            'required' => 'Truong :attribute khong duoc bo trong!',
//            'password.min' => 'Truong :attribute phai lon hon 6 ki tu!',
//            'confirm_password.min' => 'Truong :attribute phai lon hon 6 ki tu!',
//            'confirm_password.same' => 'Truong :attribute va password phai giong nhau!',
//            'email.regex' => 'Truong :attribute khong hop le!',
//            'unique' => 'Truong :attribute da ton tai!'
//        ]);
//
//        return $validator;
//    }

    public function validation(array $params): array
    {
        $messages = [];

        // Kiểm tra username
        if (!isset($params['username']) || empty($params['username'])) {
            $messages['username'] = 'Username không được bỏ trống!';
        } else {
            $checkUsername = DbUser::where('username', $params['username'])->exists();
             if ($checkUsername) {
                 $messages['username'] = 'Username đã tồn tại!';
             }
        }

        // Kiểm tra password
        if (!isset($params['password']) || empty($params['password'])) {
            $messages['password'] = 'Password không được bỏ trống!';
        } elseif (strlen($params['password']) < 6) {
            $messages['password'] = 'Password phải có ít nhất 6 ký tự!';
        }

        // Kiểm tra email
        if (!isset($params['email']) || empty($params['email'])) {
            $messages['email'] = 'Email không được bỏ trống!';
        } elseif (!filter_var($params['email'], FILTER_VALIDATE_EMAIL)) {
            $messages['email'] = 'Email không hợp lệ!';
        } else {
            $checkEmail = DbUser::where('username', $params['username'])->exists();
             if ($checkEmail) {
                 $messages['email'] = 'Email đã tồn tại!';
             }
        }

        if (!isset($params['confirm_password']) || empty($params['confirm_password'])) {
            $messages['confirm_password'] = 'Confirm_password không được bỏ trống!';
        } elseif (strlen($params['confirm_password']) < 6) {
            $messages['confirm_password'] = 'Confirm_password phải có ít nhất 6 ký tự!';
        } elseif ($params['confirm_password'] !== $params['password']) {
            $messages['confirm_password'] = 'Confirm_password và password không khớp!';
        }

        return $messages;
    }
}