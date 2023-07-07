<?php

namespace App\Domains\User\Service;

use App\Domains\User\Model\DbUser;
use App\Domains\User\Repository\UserRepository;

class UserService
{
    protected UserRepository $userRepository;

    public function __construct(UserRepository $userRepository)
    {
        $this->userRepository = $userRepository;
    }

    public function register(array $params)
    {
        $params['password'] = md5($params['password']);
        $params['jwt_token'] = $this->generateJWT($params, env('JWT_SECRET'));
        return $this->userRepository->create($params);
    }

    public function generateJWT($payload, $secret_key): string
    {
        $header = base64_encode(json_encode(array('alg' => 'HS256', 'typ' => 'JWT')));
        $payload = base64_encode(json_encode($payload));
        $signature = base64_encode(hash_hmac('sha256', "$header.$payload", $secret_key, true));
        return "$header.$payload.$signature";
    }

    public function login(array $params)
    {
        $user = DbUser::query()->select()->where('username', $params['username'])->first();
        return $user->jwt_token;
    }

    public function updatePassword(array $params): bool
    {
        $user = DbUser::query()->select()
            ->where('username' ,$params['username'])
            ->where('email', $params['email'])
            ->first();
        $currentPassword = $user->password;
        $newPassword = $params['new_password'];
        $this->userRepository->update($user->id, $params);

        if ($newPassword !== $currentPassword) {
            $params['password'] = md5($newPassword);
            $this->userRepository->update($user->id, $params);
            return true;
        }

        return false;

//        $to = "lcthangg22@gmail.com";
//        $subject = "Tiêu đề email";
//        $message = "Nội dung email";
//
//        $headers = "From: dttmcndy.lct@gmail.com\r\n";
//        $headers .= "Reply-To: lcthangg22@gmail.com\r\n";
//        $headers .= "Content-Type: text/plain; charset=UTF-8\r\n";
//
//        $mailSent = mail($to, $subject, $message, $headers);
//
//        if ($mailSent) {
//            return true;
//        } else {
//            return false;
//        }
    }
}