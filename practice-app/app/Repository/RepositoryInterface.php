<?php

namespace App\Repository;

interface RepositoryInterface
{
    public function find(int $id);

    public function create(array $attributes);

    public function update(int $id, array $attributes);

    public function delete(int $id);
}