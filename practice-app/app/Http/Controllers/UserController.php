<?php

namespace App\Http\Controllers;

use App\Domains\User\Service\UserService;
use App\Domains\User\Validator\LoginValidator;
use App\Domains\User\Validator\RegisterValidator;
use App\Domains\User\Validator\UpdateValidator;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class UserController extends Controller
{
    protected UserService $userService;

    public function __construct(UserService $userService)
    {
        $this->userService = $userService;
    }

    public function register(Request $request): JsonResponse
    {
        $params = $request->all();
        $validator = (new RegisterValidator())->validation($params);
        if ($validator) {
            return response()->json([
                'success' => false,
                'data' => null,
                'message' => $validator
            ], 400);
        }

        $user = $this->userService->register($params);

        return response()->json([
            'success' => true,
            'data' => $user,
        ]);
    }

    public function login(Request $request): JsonResponse
    {
        $params = $request->all();
        $validator = (new LoginValidator())->validation($params);
        $token = $this->userService->login($params);
        if ($validator) {
            return response()->json([
                'success' => false,
                'data' => null,
                'message' => $validator
            ], 400);
        }

        return response()->json([
            'success' => true,
            'data' => $token,
        ]);
    }

    public function updatePassword(Request $request): JsonResponse
    {
        $params = $request->all();
        $validator = (new UpdateValidator())->validation($params);
        if ($validator) {
            return response()->json([
                'success' => false,
                'data' => null,
                'message' => $validator
            ], 400);
        }

        $this->userService->updatePassword($params);
        return response()->json([
            'success' => true,
            'data' => 'Update password success!',
        ]);
    }
}